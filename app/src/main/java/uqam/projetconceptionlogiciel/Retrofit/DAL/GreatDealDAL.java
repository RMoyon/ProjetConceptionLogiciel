package uqam.projetconceptionlogiciel.Retrofit.DAL;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IGreatDealDAL;
import uqam.projetconceptionlogiciel.Model.GreatDeal;
import uqam.projetconceptionlogiciel.Retrofit.Body.UserPoints;
import uqam.projetconceptionlogiciel.Retrofit.RetrofitClient;
import uqam.projetconceptionlogiciel.Retrofit.Services.GreatDealService;

public class GreatDealDAL implements IGreatDealDAL {

    private static GreatDealService greatDealService;

    public GreatDealDAL() {
        greatDealService = RetrofitClient.getClient().create(GreatDealService.class);
    }

    @Override
    public Observable<Response<List<GreatDeal>>> getClosestGreatDeals(Double longitude, Double latitude, int returnNumber) {
        UserPoints userPoints = new UserPoints(longitude, latitude, returnNumber);

        return greatDealService.getClosestGreatDeals(userPoints);
    }
}
