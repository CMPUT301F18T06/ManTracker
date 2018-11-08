package project.ece301.mantracker.data;

import android.location.Location;

public class Geolocation extends Location {

    public Geolocation(String provider) {
        super(provider);
    }

    public Geolocation(Location l) {
        super(l);
    }

    public void setGeolocation(Location l) {
        super.set(l);
    }

    public LatAndLong getGeolocation() {
        return new LatAndLong(super.getLatitude(), super.getLongitude());
    }


}

final class LatAndLong {
    private final double latitude;
    private final double longitude;

    public LatAndLong(double latitude, double longitude) {
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