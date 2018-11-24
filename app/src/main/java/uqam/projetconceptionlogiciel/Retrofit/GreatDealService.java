package uqam.projetconceptionlogiciel.Retrofit;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import uqam.projetconceptionlogiciel.Model.MapPoints;
import uqam.projetconceptionlogiciel.Model.GreatDeal;

public interface GreatDealRetrofitService {
    @POST("/greatDealsClosest")
    Observable<Response<GreatDeal>> closestGreatDeals(@Body MapPoints map);
}
