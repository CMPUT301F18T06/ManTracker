package project.ece301.mantracker.Geolocation;

import android.content.Intent;

import com.google.android.gms.location.places.Place;

public interface LocationGetter {

    int PLACE_PICKER_REQUEST = 1;

    public abstract Place getLocation();

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
