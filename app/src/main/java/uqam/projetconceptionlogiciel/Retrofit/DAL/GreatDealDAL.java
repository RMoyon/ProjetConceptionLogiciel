package uqam.projetconceptionlogiciel.Retrofit.DAL;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IGreatDealDAL;
import uqam.projetconceptionlogiciel.Model.MapPoints;
import uqam.projetconceptionlogiciel.Model.GreatDeal;
import uqam.projetconceptionlogiciel.Retrofit.RetrofitClient;
import uqam.projetconceptionlogiciel.Retrofit.Services.GreatDealService;

public class GreatDealDAL implements IGreatDealDAL {

    private static GreatDealService greatDealService;

    public GreatDealDAL() {
        greatDealService = RetrofitClient.getClient("http://10.0.2.2:8000/").create(GreatDealService.class);
    }

    @Override
    public Observable<Response<GreatDeal>> closestGreatDeals(Double top, Double bottom, Double left, Double right, int returnNumber) {
        MapPoints map = new MapPoints(top, bottom, left, right, returnNumber);

        return greatDealService.closestGreatDeals(map);
    }
}
