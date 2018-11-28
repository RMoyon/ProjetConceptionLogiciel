package uqam.projetconceptionlogiciel.Retrofit.DAL;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.DAL.IUserDAL;
import uqam.projetconceptionlogiciel.Model.AuthentificationTokens;
import uqam.projetconceptionlogiciel.Model.University;
import uqam.projetconceptionlogiciel.Model.User;
import uqam.projetconceptionlogiciel.Retrofit.RetrofitClient;
import uqam.projetconceptionlogiciel.Retrofit.Services.UserService;

public class UserDAL implements IUserDAL {

    private static UserService userService;

    public UserDAL() {
        userService = RetrofitClient.getClient("http://10.0.2.2:8000/").create(UserService.class);
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

    @Override
    public Observable<Response<User>> updateUser(User user) {
        return userService.updateUser(user.getId(), user);
    }

    @Override
    public Observable<Response<User>> deleteUser(User user) {
        return userService.deleteUser(user.getId());
    }

//    @Override
//    public Observable<Response<User>> addUniversity(User user, University university){
//        return
//    }
}
