package uqam.projetconceptionlogiciel.DAL;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.Model.University;

public interface IUniversityDAL {
    Observable<Response<List<University>>> getAllUniversities();
}
