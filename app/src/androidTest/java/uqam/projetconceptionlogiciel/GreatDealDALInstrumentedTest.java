package uqam.projetconceptionlogiciel;

import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

import uqam.projetconceptionlogiciel.DAL.IGreatDealDAL;
import uqam.projetconceptionlogiciel.Retrofit.DAL.GreatDealDAL;

@RunWith(AndroidJUnit4.class)
public class GreatDealDALInstrumentedTest {

    private IGreatDealDAL greatDealDAL = new GreatDealDAL();

/*    @Test
    public void testClosestGreatDealsNumberResponse() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        greatDealDAL.closestGreatDeals(45.515812, 45.503181, -73.562812, -73.575429, 5)
                .subscribe(new Consumer<Response<GreatDeal>>() {
                    @Override
                    public void accept(Response<GreatDeal> greatDeals) {
                        Assert.assertEquals(5, (int) greatDeals.lenght);
                        latch.countDown();
                    }
                });

        latch.await();
    }

    @Test
    public void testClosestGreatDeals() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        greatDealDAL.closestGreatDeals(45.515812, 45.503181, -73.562812, -73.575429, 5)
                .subscribe(new Consumer<Response<Object []>>() {
                    @Override
                    public void accept(Response<Object []> greatDeals) {
                        Assert.assertEquals(1, (int) greatDeals[0].body().getId());
                        latch.countDown();
                    }
                });

        latch.await();
    }

    @Test
    public void testClosestGreatDealsWithinFrame() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);

        Double top = 45.515812, bottom =  45.503181, left = -73.575429, right = -73.562812;

        greatDealDAL.closestGreatDeals(top, bottom, left, right, 5)
                .subscribe(new Consumer<Response<GreatDeal>>() {
                    @Override
                    public void accept(Response<GreatDeal> greatDeals) {
                      for (int i = 0 ; i < greatDeals.length ; i++ ) {
                        Assert.assert(greatDeals[i].getInformation()[0].getLatitude() > top);
                        Assert.assert(greatDeals[i].getInformation()[0].getLatitude() < bottom);
                        Assert.assert(greatDeals[i].getInformation()[0].getLongitude() > left);
                        Assert.assert(greatDeals[i].getInformation()[0].getLongitude() < right);
                        latch.countDown();
                      }
                    }
                });

        latch.await();
    }*/
}
