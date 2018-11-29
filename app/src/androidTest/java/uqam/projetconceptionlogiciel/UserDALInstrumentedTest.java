package uqam.projetconceptionlogiciel;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.APIError.IUserAPIError;
import uqam.projetconceptionlogiciel.DAL.IUserDAL;
import uqam.projetconceptionlogiciel.Model.University;
import uqam.projetconceptionlogiciel.Model.User;
import uqam.projetconceptionlogiciel.Retrofit.APIError.UserAPIError;
import uqam.projetconceptionlogiciel.Retrofit.DAL.UserDAL;

@RunWith(AndroidJUnit4.class)
public class UserDALInstrumentedTest {

    private IUserDAL userDAL = new UserDAL();

    @Test
    public void testAuthWithRightTokens() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        userDAL.authentificateUser("LeGriffeur", "NgWEdi^5kJngbE#3fW+^")
                .subscribe(new Consumer<Response<User>>() {
                    @Override
                    public void accept(Response<User> response) {
                        Assert.assertEquals(2, (int) response.body().getId());
                        latch.countDown();
                    }
                });

        latch.await();
    }

    @Test
    public void testAuthWithWrongTokens() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        userDAL.authentificateUser("wrongLogin", "wrongPassword")
                .subscribe(new Consumer<Response<User>>() {
                    @Override
                    public void accept(Response<User> response) {
                        IUserAPIError apiError = new UserAPIError(response);
                        //L'API doit retourner un statusCode 404
                        Assert.assertTrue(apiError.authTokensAreInvalid());

                        latch.countDown();
                    }
                });

        latch.await();
    }

    @Test
    public void testPostUserMethod() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        User newUser = new User("Login", "pwd", "lastName", "firstName");

        userDAL.createUser(newUser)
                .flatMap(new Function<Response<User>, ObservableSource<Response<User>>>() {
                    @Override
                    public ObservableSource<Response<User>> apply(Response<User> response) {
                        Assert.assertTrue(response.isSuccessful());
                        return userDAL.deleteUser(response.body());
                    }
                })
                .subscribe(new Consumer<Response<User>>() {
                    @Override
                    public void accept(Response<User> response) {
                        latch.countDown();
                    }
                });

        latch.await();
    }

    @Test
    public void testPostUserMethodWithLoginAlreadyTaken() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        User newUser = new User("LeGriffeur", "pwd", "lastName", "firstName");

        userDAL.createUser(newUser)
                .subscribe(new Consumer<Response<User>>() {
                    @Override
                    public void accept(Response<User> response) {
                        IUserAPIError apiError = new UserAPIError(response);

                        Assert.assertTrue(apiError.loginAlreadyExist());

                        latch.countDown();
                    }
                });

        latch.await();
    }

    @Test
    public void testPatchUserMethod() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        User newUser = new User("Login2", "pwd", "lastName", "firsttName");
        final String newFirstName = "NewFirstName";

        userDAL.createUser(newUser)
                .flatMap(new Function<Response<User>, ObservableSource<Response<User>>>() {
                    @Override
                    public ObservableSource<Response<User>> apply(Response<User> response) {
                        response.body().setFirstName(newFirstName);
                        return userDAL.updateUser(response.body());
                    }
                })
                .flatMap(new Function<Response<User>, ObservableSource<Response<User>>>() {
                    @Override
                    public ObservableSource<Response<User>> apply(Response<User> response) {
                        Assert.assertEquals(newFirstName, response.body().getFirstName());
                        return userDAL.deleteUser(response.body());
                    }
                })
                .subscribe(new Consumer<Response<User>>() {
                    @Override
                    public void accept(Response<User> response) {
                        latch.countDown();
                    }
                });

        latch.await();
    }

    @Test
    public void testDeleteUserMethod() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        User newUser = new User("Login3", "pwd", "lastName", "firstName");

        userDAL.createUser(newUser)
                .flatMap(new Function<Response<User>, ObservableSource<Response<User>>>() {
                    @Override
                    public ObservableSource<Response<User>> apply(Response<User> response) {
                        return userDAL.deleteUser(response.body());
                    }
                })
                .subscribe(new Consumer<Response<User>>() {
                    @Override
                    public void accept(Response<User> response) {
                        Assert.assertEquals(204, response.code());
                        latch.countDown();
                    }
                });

        latch.await();
    }

    @Test
    public void testAddAndDeleteUniversityMethod() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        userDAL.addUniversity(7, 1)
                .flatMap(new Function<Response<User>, ObservableSource<Response<User>>>() {
                    @Override
                    public ObservableSource<Response<User>> apply(Response<User> response) {
                        University universityAdded = response.body().getUniversities().get(0);
                        Assert.assertEquals("UQAM", universityAdded.getName());
                        return userDAL.deleteUniversity(7, 1);
                    }
                }).subscribe(new Consumer<Response<User>>() {
            @Override
            public void accept(Response<User> response) {
                List<University> universities = response.body().getUniversities();
                Assert.assertEquals(0, universities.size());
                latch.countDown();
            }
        });

        latch.await();
    }
}
