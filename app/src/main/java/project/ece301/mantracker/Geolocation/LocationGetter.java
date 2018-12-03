/**
 * Interface Name: LocationGetter
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Interface for a class that can get locations.
 * Implement this interface if you need to use the places API to get locations.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.Geolocation;

import android.content.Intent;

import com.google.android.gms.location.places.Place;

/**
 * Interface for getting a location.
 * Implement this interface if your activity needs to be able to get a location
 * using the GooglePlaces API
 *
 * @version 1.0
 * @since 1.0
 */
public interface LocationGetter {

    int PLACE_PICKER_REQUEST = 1;

    /**
     * Start the Google Places Activity
     */
    public abstract void goToGooglePlaces();

}
