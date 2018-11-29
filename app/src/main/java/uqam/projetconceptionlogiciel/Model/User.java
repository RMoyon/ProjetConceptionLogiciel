package uqam.projetconceptionlogiciel.Model;

import java.util.List;

public class User {

    private Integer id;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private List<University> universities;
    private List<GreatDeal> interests;

    public User(String login, String password, String lastName, String firstName) {
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public List<GreatDeal> getInterests() {
        return interests;
    }

}
