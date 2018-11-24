package uqam.projetconceptionlogiciel.Model;

public class Information {

	private String streetNumber;
	private String streetName;
	private String town;
	private String postalCode;
	private Double latitude;
	private Double longitude;

	public Information(String streetNumber, String streetName, String town, String postalCode, Double latitude, Double longitude) {
		super();
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.town = town;
		this.postalCode = postalCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	@Override
	public String toString() {
		return "Information [streetNumber=" + streetNumber + ", streetName=" + streetName + ", town=" + town
				+ ", postalCode=" + postalCode + "]";
	}


}
