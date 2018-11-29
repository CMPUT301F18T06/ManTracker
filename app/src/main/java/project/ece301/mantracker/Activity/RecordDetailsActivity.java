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

import java.util.ArrayList;
import java.util.List;

import project.ece301.mantracker.MedicalProblem.BodyLocation;
import project.ece301.mantracker.MedicalProblem.ElasticSearchRecordController;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.MedicalProblem.UploadPhoto;
import project.ece301.mantracker.R;

import static project.ece301.mantracker.File.StoreData.patients;

/*This activity is initiated when a user selects a record from the record list.
It will display the current record's title, date and description.
It will also display any photos that are associated with it
* */
public class RecordDetailsActivity extends AppCompatActivity {
    private ArrayList<Record> recordList = new ArrayList<Record>();
    private ArrayList<String> photoList = new ArrayList<String>();
    private String recordDate;
    private String recordTitle;
    private String recordDescription;
    private String recordID; //used for elasticsearch
    private String associatedUsername;
    Record chosenRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_details);

        //grab the record details
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        recordID = extras.getString("RECORDID");
        associatedUsername = extras.getString("USERNAME");

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Records are queried by the current user's username
        ElasticSearchRecordController.GetRecordsWithID getRecordsIDTask = new ElasticSearchRecordController.GetRecordsWithID();
        getRecordsIDTask.execute(recordID);

        try {
            List<Record> foundRecords = getRecordsIDTask.get();
            Log.i("REACHED", "success");
            Log.i("REACHED", foundRecords.get(0).getDescription());
            recordList.add(foundRecords.get(0));
            Log.i("REACHED", "also success");
            Log.i("AddRecordTask", String.valueOf(recordList.size()));

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


        Log.i("REACHED", "REACHEDHERE");
        for(BodyLocation blocation: chosenRecord.getBodyLocationList()) {
            photoList.add(blocation.getBodyImage());
        }
        Log.i("TESTINGPHOTO", "ArrayList Length: " + String.valueOf(chosenRecord.getBodyLocationList().size()));
        Log.i("TESTINGPHOTO", "Record Title: " + chosenRecord.getTitle());
        Log.i("TESTINGPHOTO", "Record Description: " + chosenRecord.getDescription());
        for(String image: photoList) {
            Bitmap bitmap = (UploadPhoto.Decode(image));
            ImageView imageView = findViewById(R.id.BodyLocation1);
            imageView.setImageBitmap(bitmap);
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        finish(); //end this activity
    }

}
