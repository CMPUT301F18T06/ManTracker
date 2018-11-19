package project.ece301.mantracker.Geolocation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

import project.ece301.mantracker.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class GoogleMapActivityFragment extends Fragment {

    public GoogleMapActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_google_map, container, false);
    }
}
