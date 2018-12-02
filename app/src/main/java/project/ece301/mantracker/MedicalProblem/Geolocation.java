/**
 * Class Name: Geolocation
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.MedicalProblem;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Represents a Geolocation
 * Geolocations have Locations
 *
 * @version 1.0
 * @since 1.0
 */
public class Geolocation extends Location {

    /**
     * Constructs a Geolocation
     *
     * @param provider the provider to be associated with this geolocation
     */
    public Geolocation(String provider) {
        super(provider);
    }

    /**
     * Constructs a Geolocation
     * @param l the Location to be associated with this Geolocation
     */
    public Geolocation(Location l) {
        super(l);
    }

    /**
     * Sets the Location associated with this geolocation
     * @param l the Location to be associated with this geolocation.
     */
    public void setGeolocation(Location l) {
        super.set(l);
    }

    /**
     * Gets the Latitude and longitude of this geolocation
     * @return a LatAndLong representing the latitude and longitude of this Geolocation
     */
    public LatAndLong getGeolocation() {
        return new LatAndLong(super.getLatitude(), super.getLongitude());
    }


}

/**
 * Represents a Latitude and Longitude
 *
 * @version 1.0
 * @since 1.0
 */
final class LatAndLong {
    private final double latitude;
    private final double longitude;

    /**
     * Constructs a LatAndLong object
     * @param latitude the latitude
     * @param longitude the longitude
     */
    public LatAndLong(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Constructs a LatAndLong object
     * @param latlng a latlng object
     */
    public LatAndLong(LatLng latlng) {
        this.latitude = latlng.latitude;
        this.longitude = latlng.longitude;
    }

    /**
     * Get latitude
     * @return latitude of the object
     */
    public double getLat() {
        return latitude;
    }

    /**
     * Get longitude
     * @return longitude of the object
     */
    public double getLong() {
        return longitude;
    }
}