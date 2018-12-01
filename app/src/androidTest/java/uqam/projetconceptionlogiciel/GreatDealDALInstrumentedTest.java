package uqam.projetconceptionlogiciel;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.reactivex.functions.Consumer;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IGreatDealDAL;
import uqam.projetconceptionlogiciel.Model.GreatDeal;
import uqam.projetconceptionlogiciel.Retrofit.DAL.GreatDealDAL;

@RunWith(AndroidJUnit4.class)
public class GreatDealDALInstrumentedTest {

    private IGreatDealDAL greatDealDAL = new GreatDealDAL();

    @Test
    public void testGetClosestGreatDeals() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        greatDealDAL.getClosestGreatDeals(-73.569120, 45.509496, 10)
                .subscribe(new Consumer<Response<List<GreatDeal>>>() {
                    @Override
                    public void accept(Response<List<GreatDeal>> greatDeals) {
                        Assert.assertEquals(3, greatDeals.body().size());
                        latch.countDown();
                    }
                });

        latch.await();
    }

    @Test
    public void testGetClosestGreatDealsWithNumberOfResponsesLimited() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        greatDealDAL.getClosestGreatDeals(-73.569120, 45.509496, 2)
                .subscribe(new Consumer<Response<List<GreatDeal>>>() {
                    @Override
                    public void accept(Response<List<GreatDeal>> greatDeals) {
                        Assert.assertEquals(2, greatDeals.body().size());
                        latch.countDown();
                    }
                });

        latch.await();
    }
}
