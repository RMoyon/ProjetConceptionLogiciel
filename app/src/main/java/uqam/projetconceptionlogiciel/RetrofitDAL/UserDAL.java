package uqam.projetconceptionlogiciel.RetrofitDAL;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IUserDAL;
import uqam.projetconceptionlogiciel.Model.AuthentificationTokens;
import uqam.projetconceptionlogiciel.Model.User;
import uqam.projetconceptionlogiciel.Retrofit.RetrofitClient;
import uqam.projetconceptionlogiciel.Retrofit.UserRetrofitService;

public class UserDAL implements IUserDAL {

    private static UserRetrofitService userService;

    public UserDAL() {
        userService = RetrofitClient.getClient("http://10.0.2.2:8000/").create(UserRetrofitService.class);
    }

    @Override
    public Observable<Response<User>> authentificateUser(String login, String password) {
        AuthentificationTokens authTokens = new AuthentificationTokens(login, password);

        return userService.authentificateUser(authTokens);
    }

    @Override
    public Observable<Response<User>> createUser(User newUser) {
        return userService.createUser(newUser);
    }
}
