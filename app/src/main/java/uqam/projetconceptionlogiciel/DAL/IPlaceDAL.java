package uqam.projetconceptionlogiciel.DAL;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.Model.Place;

public interface IPlaceDAL {
    Observable<Response<List<Place>>> getClosestPlaces(Double top, Double bottom, Double left, Double right, int returnNumber);
}
