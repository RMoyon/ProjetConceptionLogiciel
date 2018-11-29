package uqam.projetconceptionlogiciel.Retrofit.DAL;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IPlaceDAL;
import uqam.projetconceptionlogiciel.Model.Place;
import uqam.projetconceptionlogiciel.Retrofit.Body.MapPoints;
import uqam.projetconceptionlogiciel.Retrofit.RetrofitClient;
import uqam.projetconceptionlogiciel.Retrofit.Services.PlaceService;

public class PlaceDAL implements IPlaceDAL {

    private static PlaceService placeService;

    public PlaceDAL(){
        placeService = RetrofitClient.getClient().create(PlaceService.class);
    }

    @Override
    public Observable<Response<List<Place>>> getClosestPlaces(Double top, Double bottom, Double left, Double right, int returnNumber) {
        MapPoints mapPoints = new MapPoints(top,bottom,left,right,returnNumber);

        return placeService.getClosestPlaces(mapPoints);
    }
}
