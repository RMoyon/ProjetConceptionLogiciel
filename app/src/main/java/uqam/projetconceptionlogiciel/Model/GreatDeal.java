package uqam.projetconceptionlogiciel.Model;

import java.util.List;

public class GreatDeal {

	private Integer id;
	private String typeOfGreatDeal;
	private String name;
	private String description;
	private List<Tag> tags;
	private List<Period> periods;
	private List<Place> places;

	public Integer getId() {
		return id;
	}

	public String getType() {
		return typeOfGreatDeal;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public List<Period> getPeriods() {
		return periods;
	}

	public List<Place> getPlaces() {
		return places;
	}
}
