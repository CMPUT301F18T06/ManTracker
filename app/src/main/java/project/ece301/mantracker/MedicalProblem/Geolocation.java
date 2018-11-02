package project.ece301.mantracker.MedicalProblem;

import android.location.Location;

public class Geolocation extends Location {

    public Geolocation(String provider) {
        super(provider);
    }

    public Geolocation(Location l) {
        super(l);
    }


}
