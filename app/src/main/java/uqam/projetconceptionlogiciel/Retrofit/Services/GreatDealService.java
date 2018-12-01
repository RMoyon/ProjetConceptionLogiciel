package uqam.projetconceptionlogiciel.Retrofit.Services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import uqam.projetconceptionlogiciel.Model.GreatDeal;
import uqam.projetconceptionlogiciel.Retrofit.Body.UserPoints;

public interface GreatDealService {
    @POST("/greatdeals/closest")
    Observable<Response<List<GreatDeal>>> getClosestGreatDeals(@Body UserPoints userPoints);
}
