package uqam.projetconceptionlogiciel.DAL;

import io.reactivex.Observable;
import retrofit2.Response;
import uqam.projetconceptionlogiciel.Model.University;
import uqam.projetconceptionlogiciel.Model.User;

public interface IUserDAL {

    Observable<Response<User>> authentificateUser(String login, String password);

    Observable<Response<User>> createUser(User newUser);

    Observable<Response<User>> updateUser(User user);

    Observable<Response<User>> deleteUser(User user);

    Observable<Response<User>> addUniversity(int idUser, int idUniversity);
}
