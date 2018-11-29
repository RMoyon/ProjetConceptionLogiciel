package uqam.projetconceptionlogiciel.DAL;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.Model.GreatDeal;

public interface IGreatDealDAL {

    Observable<Response<List<GreatDeal>>> getClosestGreatDeals(Double longitude, Double latitude, int returnNumber);
}
