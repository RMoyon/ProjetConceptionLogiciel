package uqam.projetconceptionlogiciel.Retrofit.Services;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import uqam.projetconceptionlogiciel.Model.MapPoints;
import uqam.projetconceptionlogiciel.Model.GreatDeal;

public interface GreatDealService {
    @POST("/greatDealsClosest")
    Observable<Response<GreatDeal>> closestGreatDeals(@Body MapPoints map);
}
