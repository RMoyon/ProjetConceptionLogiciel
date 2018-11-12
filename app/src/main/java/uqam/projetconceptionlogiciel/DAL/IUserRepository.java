package uqam.projetconceptionlogiciel.DAL;

import uqam.projetconceptionlogiciel.Model.User;
import io.reactivex.Observable;

public interface IUserRepository {

    Observable<User> getUserById(int id);
}
