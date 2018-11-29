package uqam.projetconceptionlogiciel.Retrofit.Services;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.HTTP;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import uqam.projetconceptionlogiciel.Retrofit.Body.AuthentificationTokens;
import uqam.projetconceptionlogiciel.Retrofit.Body.Linker;
import uqam.projetconceptionlogiciel.Model.User;

public interface UserService {
    @POST("/users/auth")
    Observable<Response<User>> authentificateUser(@Body AuthentificationTokens authTokens);

    @POST("/users")
    Observable<Response<User>> createUser(@Body User newUser);

    @PATCH("/users/{idUser}")
    Observable<Response<User>> updateUser(@Path("idUser") int idUser, @Body User user);

    @DELETE("/users/{idUser}")
    Observable<Response<User>> deleteUser(@Path("idUser") int idUser);

    @POST("users/university/")
    Observable<Response<User>> addUniversity(@Body Linker linker);

    @HTTP(method = "DELETE", path = "users/university/", hasBody = true)
    Observable<Response<User>> deleteUniversity(@Body Linker linker);
}
