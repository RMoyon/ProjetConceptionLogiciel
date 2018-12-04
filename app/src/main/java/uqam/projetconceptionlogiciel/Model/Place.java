package uqam.projetconceptionlogiciel.Model;

import java.util.List;

public class Place {
    private int id;
    private String streetNumber;
    private String streetName;
    private String town;
    private String postalCode;
    private Double latitude;
    private Double longitude;
    private String email;
    private String phoneNumber;
    private String websiteUrl;
    private List<GreatDeal> greatDeals;

    public int getId() { return id; }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getTown() {
        return town;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public List<GreatDeal> getGreatDeals() {
        return greatDeals;
    }
}
