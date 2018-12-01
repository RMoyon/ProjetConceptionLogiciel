package uqam.projetconceptionlogiciel.Retrofit.Services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import uqam.projetconceptionlogiciel.Model.Place;
import uqam.projetconceptionlogiciel.Retrofit.Body.MapPoints;

public interface PlaceService {
    @POST("/places/closest")
    Observable<Response<List<Place>>> getClosestPlaces(@Body MapPoints mapPoints);
}
