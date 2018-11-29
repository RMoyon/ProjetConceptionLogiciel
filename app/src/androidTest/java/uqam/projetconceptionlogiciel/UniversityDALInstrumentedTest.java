package uqam.projetconceptionlogiciel;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.reactivex.functions.Consumer;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IUniversityDAL;
import uqam.projetconceptionlogiciel.Model.University;
import uqam.projetconceptionlogiciel.Retrofit.DAL.UniversityDAL;

@RunWith(AndroidJUnit4.class)
public class UniversityDALInstrumentedTest {
    IUniversityDAL universityDAL = new UniversityDAL();

    @Test

    public void testGetAllUniversitiesMethod() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        universityDAL.getAllUniversities()
                .subscribe(new Consumer<Response<List<University>>>() {
                    @Override
                    public void accept(Response<List<University>> response) {
                        Assert.assertTrue(response.isSuccessful());
                        Assert.assertEquals("ESG", response.body().get(0).getName());
                        latch.countDown();
                    }
                });

        latch.await();

    }
}
