package uqam.projetconceptionlogiciel.Model;

public class GreatDeal {

	private Integer id;
	private String type;
	private String name;
	private String description;

	private Tag[] tags;
	private Period[] periods;
	private Information information;
	
	// Question ? Utilit� ?
	private User[] personInterested;
	private User[] personParticipating;
	
	public GreatDeal(Integer id, String type, String name, String description) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.description = description;
	}

	//=== GETTERS & SETTERS ===
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public User[] getPersonInterested() {
		return personInterested;
	}

	public User[] getPersonParticipating() {
		return personParticipating;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Tag[] getTags() {
		return tags;
	}

	public Period[] getPeriods() {
		return periods;
	}
	
	public Information getInformation() {
		return information;
	}
	
	
}
