package uqam.projetconceptionlogiciel.Retrofit.Services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import uqam.projetconceptionlogiciel.Model.University;

public interface UniversityService {
    @GET("/universities")
    Observable<Response<List<University>>> getAllUniversities();
}
