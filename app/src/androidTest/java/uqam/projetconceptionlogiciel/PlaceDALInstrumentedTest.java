package uqam.projetconceptionlogiciel;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.reactivex.functions.Consumer;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IPlaceDAL;
import uqam.projetconceptionlogiciel.Model.Place;
import uqam.projetconceptionlogiciel.Retrofit.DAL.PlaceDAL;

public class PlaceDALInstrumentedTest {

    IPlaceDAL placeDAL = new PlaceDAL();

    @Test
    public void testGetClosestPlaces() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        placeDAL.getClosestPlaces(	45.515812, 45.503181, -73.575429, -73.562812, 5)
                .subscribe(new Consumer<Response<List<Place>>>() {
                    @Override
                    public void accept(Response<List<Place>> response) {
                        Assert.assertEquals(1, response.body().size());
                        latch.countDown();
                    }
                });

        latch.await();
    }

}
