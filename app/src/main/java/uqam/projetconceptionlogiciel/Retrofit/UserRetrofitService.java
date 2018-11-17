package uqam.projetconceptionlogiciel.Retrofit;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import uqam.projetconceptionlogiciel.Model.AuthentificationTokens;
import uqam.projetconceptionlogiciel.Model.User;

public interface UserRetrofitService {
    @POST("/users/auth")
    Observable<Response<User>> authentificateUser(@Body AuthentificationTokens authTokens);

    @POST("/users")
    Observable<Response<User>> createUser(@Body User newUser);
}
