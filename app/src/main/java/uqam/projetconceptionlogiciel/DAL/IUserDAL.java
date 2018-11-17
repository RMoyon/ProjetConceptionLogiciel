package uqam.projetconceptionlogiciel.DAL;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.Model.User;

public interface IUserDAL {

    Observable<Response<User>> authentificateUser(String login, String password);

    Observable<Response<User>> createUser(User newUser);
}
