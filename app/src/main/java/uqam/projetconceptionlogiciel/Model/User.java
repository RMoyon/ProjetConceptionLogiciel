package uqam.projetconceptionlogiciel.Model;

public class User {
	
	private Integer id;
	private String login;
	private String password;
	private String lastName;
	private String firstName;
	
	private GreatDeal canParticipate[];
	private GreatDeal isParticipating[];
	private University studiesAt[];
	
	public User(){}
	public User(Integer id, String login, String password, String lastName, String firstName) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
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
	public Integer getId() {
		return id;
	}
	public GreatDeal[] getCanParticipate() {
		return canParticipate;
	}
	public GreatDeal[] getIsParticipating() {
		return isParticipating;
	}
	public University[] getStudiesAt() {
		return studiesAt;
	}
	
	

}
