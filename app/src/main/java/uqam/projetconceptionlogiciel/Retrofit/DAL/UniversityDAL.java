package uqam.projetconceptionlogiciel.Retrofit.DAL;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IUniversityDAL;
import uqam.projetconceptionlogiciel.Model.University;
import uqam.projetconceptionlogiciel.Retrofit.RetrofitClient;
import uqam.projetconceptionlogiciel.Retrofit.Services.UniversityService;

public class UniversityDAL implements IUniversityDAL {

    private static UniversityService universityService;

    public UniversityDAL() {
        this.universityService = RetrofitClient.getClient().create(UniversityService.class);
    }

    @Override
    public Observable<Response<List<University>>> getAllUniversities() {
        return universityService.getAllUniversities();
    }
}
