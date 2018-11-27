package uqam.projetconceptionlogiciel.Retrofit.Services;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import uqam.projetconceptionlogiciel.Model.AuthentificationTokens;
import uqam.projetconceptionlogiciel.Model.User;

public interface UserService {
    @POST("/users/auth")
    Observable<Response<User>> authentificateUser(@Body AuthentificationTokens authTokens);

    @POST("/users")
    Observable<Response<User>> createUser(@Body User newUser);

    @PATCH("/users/{idUser}")
    Observable<Response<User>> updateUser(@Path("idUser") int idUser, @Body User user);
}
