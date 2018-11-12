package uqam.projetconceptionlogiciel.Retrofit;

import uqam.projetconceptionlogiciel.Model.User;
import retrofit2.http.GET;
import retrofit2.http.Path;
import io.reactivex.Observable;

public interface UserRetrofitService {
    @GET("/users/{id}")
    Observable<User> getUserById(@Path("id") int idUser);
}
