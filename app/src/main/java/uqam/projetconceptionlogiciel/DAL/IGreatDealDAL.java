package uqam.projetconceptionlogiciel.DAL;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.Model.GreatDeal;

public interface IGreatDealDAL {

    Observable<Response<GreatDeal>> closestGreatDeals(Double top, Double bottom, Double left, Double right, int returnNumber);
}
