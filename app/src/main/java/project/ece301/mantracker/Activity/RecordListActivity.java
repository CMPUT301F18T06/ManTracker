package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import project.ece301.mantracker.MedicalProblem.ElasticSearchRecordController;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;

import static project.ece301.mantracker.File.StoreData.patients;

public class RecordListActivity extends AppCompatActivity {
    private ListView recordListView;
    private ArrayAdapter<Record> adapter;
    private ArrayList<Record> recordList;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_records);
        recordListView = findViewById(R.id.recordList);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //contains all records associated with problem NOTE this will change when we merge with MEDICAL PROBLEM
        recordList = new ArrayList<Record>();
        //update the record listview
        adapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_1, recordList);
        recordListView.setAdapter(adapter);

        //get the index of the record that was selected
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        index = extras.getInt("USERINDEX");
        String title = extras.getString("PROBLEMTITLE");

        //set the patient username and problem title header
        TextView username_text = findViewById(R.id.patientUsername);
        username_text.setText(patients.get(index).getUsername().toString());
        TextView title_text = findViewById(R.id.recordTitleTextView);
        title_text.setText(title);

        //fetch from elasticsearch and populate the records list
        //Records are queried by the current user's username
        ElasticSearchRecordController.GetRecordsTask getRecordsTask = new ElasticSearchRecordController.GetRecordsTask();
        getRecordsTask.execute(patients.get(index).getUsername().toString());
        Log.i("AddRecordTask", "Username: " + patients.get(index).getUsername().toString());

        try {
            List<Record> foundRecords = getRecordsTask.get();
            recordList.addAll(foundRecords);
        } catch (Exception e) {
            Log.i("AddRecordTask", "Failed to get the tweets from the async object");
        }

        adapter.notifyDataSetChanged();

    }

    public void toAddRecordActivity(View view) {
        //Move to add record activity
        Intent intent = new Intent(RecordListActivity.this, AddRecordActivity.class);
        Bundle extras = new Bundle();
        extras.putString("USERNAME", patients.get(index).getUsername().toString());
        intent.putExtras(extras); //pass the patient username to the add record activity
        startActivity(intent);
    }

}
