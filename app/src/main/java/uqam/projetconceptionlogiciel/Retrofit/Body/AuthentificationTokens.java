package uqam.projetconceptionlogiciel.Retrofit.Body;

public class AuthentificationTokens {
    private String login;

    private String password;

    public AuthentificationTokens(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
