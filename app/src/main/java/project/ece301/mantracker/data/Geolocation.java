package project.ece301.mantracker.data;

public class Geolocation {
    private final double latitude;
    private final double longitude;

    public Geolocation(double latitude, double longitude) {
        // TODO: validate between |180s| and |90s|
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLat() {
        return latitude;
    }

    public double getLong() {
        return longitude;
    }
}