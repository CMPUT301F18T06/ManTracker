package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import project.ece301.mantracker.MedicalProblem.BodyLocation;
import project.ece301.mantracker.MedicalProblem.ElasticSearchRecordController;
import project.ece301.mantracker.MedicalProblem.Geolocation;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.MedicalProblem.UploadPhoto;
import project.ece301.mantracker.R;

import static project.ece301.mantracker.File.StoreData.patients;

/*This activity is initiated when a user selects a record from the record list.
It will display the current record's title, date and description.
It will also display any photos that are associated with it
* */
public class RecordDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ArrayList<Record> recordList = new ArrayList<Record>();
    private ArrayList<String> photoList = new ArrayList<String>();

    int index, problemIndex, recordIndex;
    private String recordID; //used for elasticsearch
    private String associatedUsername;
    Record chosenRecord;

    private GoogleMap mMap;
    private final int DEFAULT_ZOOM = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_details);

        //grab the record details
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        recordID = extras.getString("RECORDID");
        associatedUsername = extras.getString("USERNAME");


        // offline index
        index = intent.getExtras().getInt("USERINDEX");
        problemIndex = intent.getExtras().getInt("ProblemIndex");
        recordIndex = intent.getExtras().getInt("RECORDINDEX");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Records are queried by the current user's username
        ElasticSearchRecordController.GetRecordsWithID getRecordsIDTask = new ElasticSearchRecordController.GetRecordsWithID();
        getRecordsIDTask.execute(recordID);

        try {
            List<Record> foundRecords = getRecordsIDTask.get();
            recordList.add(foundRecords.get(0));

        } catch (Exception e) {
            Log.i("AddRecordTask", "Failed to get the records from the async object");
        }

        chosenRecord = recordList.get(0);

        //print out the record details
        TextView title_text = findViewById(R.id.recordDetailsTitleHeader);
        title_text.setText(chosenRecord.getTitle());
        TextView description_text = findViewById(R.id.recordDetailsDescription);
        description_text.setText(chosenRecord.getDescription());
        TextView date = findViewById(R.id.recordDetailsDate);
        date.setText(chosenRecord.getDate());
        TextView username = findViewById(R.id.recordDetailUsername);
        username.setText(associatedUsername);

    }


    public void RecordPhotos(View view){
        Intent intent = new Intent(this, RecordPhotos.class);
        Bundle extras = new Bundle();

        extras.putInt("USERINDEX", index);
        extras.putInt("ProblemIndex", problemIndex);
        extras.putInt("RecordIndex", recordIndex);

        intent.putExtras(extras);

        startActivity(intent);
    }

    public void RecordBodyImages(View view){
        Intent intent = new Intent(this, RecordBodyImages.class);
        Bundle extras = new Bundle();

        extras.putInt("USERINDEX", index);
        extras.putInt("ProblemIndex", problemIndex);
        extras.putInt("RecordIndex", recordIndex);

        intent.putExtras(extras);

        startActivity(intent);
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        finish(); //end this activity
//    }

    public void toUserProfile(View view)
    {
        //send the patient information to the user profile activity
        Intent userProfileIntent = new Intent(RecordDetailsActivity.this, UserProfileActivity.class );
        userProfileIntent.putExtra("USERINDEX", index);
        startActivity(userProfileIntent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Do setup activities here

        // Get the current location of the device and set the position of the map.
        goToLocation(getRecordLocation());
    }

    private LatLng getRecordLocation() {
        try {
            return chosenRecord.getGeoLocation().getLatLng();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void goToLocation(LatLng location) {
        try {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, DEFAULT_ZOOM));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
