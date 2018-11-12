package uqam.projetconceptionlogiciel.Model;

public class University {

	private Integer id;
	private String name;
	
	public University(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
}
