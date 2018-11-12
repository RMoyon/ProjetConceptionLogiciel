package uqam.projetconceptionlogiciel.DAL;

import io.reactivex.Observable;
import uqam.projetconceptionlogiciel.Model.User;
import uqam.projetconceptionlogiciel.Retrofit.RetrofitClient;
import uqam.projetconceptionlogiciel.Retrofit.UserRetrofitService;

public class UserRepository implements IUserRepository {

    private static UserRetrofitService userService;

    public UserRepository() {
        userService = RetrofitClient.getClient("http://10.0.2.2:8000/").create(UserRetrofitService.class);
    }

    @Override
    public Observable<User> getUserById(int id) {
        return userService.getUserById(id);
    }
}
