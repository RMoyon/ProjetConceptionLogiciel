package uqam.projetconceptionlogiciel;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import io.reactivex.functions.Consumer;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.APIError.IUserAPIError;
import uqam.projetconceptionlogiciel.DAL.IUserDAL;
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

        User newUser = new User("unexistantLogin", "pwd", "name", "firstname");

        userDAL.createUser(newUser).subscribe(new Consumer<Response<User>>() {
            @Override
            public void accept(Response<User> response) {
                IUserAPIError apiError = new UserAPIError(response);

                Boolean testShouldPass = response.isSuccessful() || apiError.loginAlreadyExist();
                Assert.assertTrue(testShouldPass);

                latch.countDown();
            }
        });

        latch.await();
    }

    @Test
    public void testPatchUserMethod() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

       // User user

        //userDAL.updateUser(1, );
        latch.countDown();

        latch.await();
    }
}
