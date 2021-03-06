/**
 * Class Name: AddRecordActivity
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Activity for adding a record.
 * This activity allows the user to add and save a new record.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.Activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import project.ece301.mantracker.Geolocation.LocationGetter;
import project.ece301.mantracker.MedicalProblem.Body;
import project.ece301.mantracker.MedicalProblem.BodyLocation;
import project.ece301.mantracker.MedicalProblem.ElasticSearchRecordController;
import project.ece301.mantracker.MedicalProblem.Geolocation;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Photo;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.MedicalProblem.UploadPhoto;
import project.ece301.mantracker.R;

import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;
import static project.ece301.mantracker.File.StoreData.saveInFile;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.Encode;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.UploadFromCamera;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.UploadFromGallery;

public class AddRecordActivity extends AppCompatActivity implements LocationGetter {

    //variables to be used
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private EditText enteredTitle;
    private EditText enteredComment;
    private Button dateButton;
    private TextView dateTextView;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String dateString;
    private String problemID;
    private String newDate;
    private String nameMsg;

    private int index,ProblemIndex;

    String encodedImage = null;

    private Button locationButton;
    private LatLng latlng;
    private final int PLACE_PICKER_REQUEST = 3;

    public static ArrayList<BodyLocation> bodyLocations = new ArrayList<BodyLocation>();
    private ArrayList<String> photos = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_record);
        dateTextView = findViewById(R.id.date);
        dateButton = findViewById(R.id.dateButton);
        locationButton = findViewById(R.id.addLocationButton);

        //set click listener. We want to launch a popup when the user taps on the date button
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddRecordActivity.this,
                        android.R.style.Theme_Material_Light_Dialog, dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override //autogenerated
            public void onDateSet(DatePicker view, int newYear, int newMonth, int dayOfMonth) {
                year = newYear;
                month = newMonth + 1; // datepicker is zero indexed so January would be month 0 etc.
                day = dayOfMonth;

                dateString = year + "-" + month + "-" + day;
                dateTextView.setText(dateString);
            }
        };

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGooglePlaces();
            }
        });

        //set the date for the record as current date by default
        //https://www.baeldung.com/java-year-month-day
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //set the current date as default
        dateTextView.setText(dateFormat.format(new Date()));

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        problemID = extras.getString("PROBLEMID");
        ProblemIndex = extras.getInt("ProblemIndex");
        index = extras.getInt("USERINDEX");

    }

    @Override
    protected void onStart(){
        super.onStart();

        TextView uploadPhotoCount = (TextView) findViewById(R.id.uploadPhotoCount1);
        uploadPhotoCount.setText(Integer.toString(photos.size()));
    }


    /**
     * Saves a new record based on the fields entered by the user into the app's UI.
     * The new record is posted to elastic search.
     *
     * @param view the save record button
     */
    public void saveRecord(View view) {
        //Create a new record that will be passed back to the record list activity
        Record record = new Record();

        for(int i =0; i<bodyLocations.size();i++){
            record.addBodyLocation(bodyLocations.get(i));
        }
        bodyLocations.clear();

        for(int i =0; i<photos.size();i++){
            record.addPhoto(photos.get(i));
        }

        //store the entered title and description in the record
        enteredTitle = findViewById(R.id.titleInputBox);
        enteredComment = findViewById(R.id.commentInputBox);
        newDate = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second;


        //will need to update this as other functionality is required
        record.setDescription(enteredComment.getText().toString());
        record.setTitle(enteredTitle.getText().toString());
        record.setDate(newDate);
        record.setProblemID(problemID);
        record.setAssociatedPatient(patients.get(index).getUsername().toString());

        if (latlng != null) {
            // Set geolocation
            Location temp = new Location(LocationManager.GPS_PROVIDER);
            temp.setLatitude(latlng.latitude);
            temp.setLongitude(latlng.longitude);
            record.setGeoLocation(new Geolocation(temp));
            record.setLocationName(nameMsg);
        }

        // add record in the offline file
        Patient patient = patients.get(index);
        MedicalProblem problem = patient.getProblem(ProblemIndex);
        problem.addRecord(record);

        patient.setProblem(problem,ProblemIndex);

        patients.set(index,patient);
        saveInFile(this); //save locally

        //post to elasticsearch
        ElasticSearchRecordController.AddRecordTask addRecordsTask = new ElasticSearchRecordController.AddRecordTask();
        addRecordsTask.execute(record);

        //wait a few seconds for es to upload. dunno if this is necessary
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {

        }

        finish();

    }

    /**
     * Starts a BodyLocationActivity activity.
     *
     * @param view the add body photo button
     */
    public void BodyLocationPhotos(View view){
        Intent intent = new Intent(this, BodyLocationActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("USERINDEX", index);
        extras.putInt("ProblemIndex", ProblemIndex);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * Displays a dialog asking the user if they want to upload existing photos or take a photo
     * with the camera.
     *
     * @param view the upload record photos button
     */
    public void UploadPhotos(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to upload an existing photo or take a camera picture?");
        alertDialogBuilder.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UploadPhoto.CheckPermissionsCamera(AddRecordActivity.this);


            }
        });
        alertDialogBuilder.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UploadPhoto.CheckPermissionsGallery(AddRecordActivity.this);
            }
        });
        alertDialogBuilder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

            try {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                encodedImage = Encode(image, Bitmap.CompressFormat.JPEG, 100);
                photos.add(encodedImage);
            }
            catch(NullPointerException ex){
                Toast.makeText(this, "Unable to upload photo. Please try again.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        else if (requestCode == 2 && resultCode == RESULT_OK && null != data) {

            Uri imageSelected = data.getData();

            try{
                Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(imageSelected));
                encodedImage = Encode(bitmap, Bitmap.CompressFormat.JPEG, 100);
                photos.add(encodedImage);
            }
            catch (NullPointerException ex){
                Toast.makeText(this, "Unable to upload photo. Please try again.",
                        Toast.LENGTH_SHORT).show();
            }
            catch(FileNotFoundException ex){
                Toast.makeText(this, "Unable to upload photo. Please try again.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        else if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                nameMsg = String.format("Place: %s", place.getName());
                String latlngMsg = String.format("Place: %s", place.getLatLng());
                Toast.makeText(this, nameMsg + "\n" + latlngMsg, Toast.LENGTH_LONG).show();
                latlng = place.getLatLng();
                updatePlaceName(place.getName().toString());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    UploadFromCamera(this);
                }
                else {
                    // User denied the message do Nothing
                }
                break;
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    UploadFromGallery(this);
                } else {
                    // User denied the message do Nothing
                }
                break;
        }
    }

    @Override
    public void goToGooglePlaces() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (Exception e) {
            Log.d("Place", "Error starting Google Places Activity");
        }
    }

    private void updatePlaceName(String _placeName) {
        this.locationButton.setText(_placeName);
    }
}