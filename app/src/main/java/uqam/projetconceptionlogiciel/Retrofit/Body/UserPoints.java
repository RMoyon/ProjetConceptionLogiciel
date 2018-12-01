package uqam.projetconceptionlogiciel.Retrofit.Body;

public class UserPoints {
    private double longitude;

    private double latitude;

    private int returnNumber;


    public UserPoints(double longitude, double latitude, int returnNumber) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.returnNumber = returnNumber;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getReturnNumber() {
        return returnNumber;
    }
}
