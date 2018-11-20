package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.ece301.mantracker.MedicalProblem.BodyLocation;
import project.ece301.mantracker.MedicalProblem.ElasticSearchRecordController;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;

import static project.ece301.mantracker.File.StoreData.patients;

public class RecordListActivity extends AppCompatActivity {
    private ListView recordListView;
    private ArrayAdapter<Record> adapter;
    private ArrayList<Record> recordList;
    int index;
    int problemIndex;
    private String problemID;
    private String problemDescription;
    private String problemDate;
    private ArrayList<String> photoStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_records);
        recordListView = findViewById(R.id.recordList);

        recordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //when user selects a problem from the list
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //go to the patient record list
                Intent recordDetailsSwitch = new Intent(RecordListActivity.this, RecordDetailsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("RECORDID", recordList.get(position).getID());

                recordDetailsSwitch.putExtras(extras);
                startActivity(recordDetailsSwitch);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //contains all records associated with problem NOTE this will change when we merge with MEDICAL PROBLEM
        recordList = new ArrayList<Record>();
        //update the record listview
        adapter = new ArrayAdapter<Record>(this, R.layout.problem_list_item, recordList);
        recordListView.setAdapter(adapter);

        String title = "Record Title";
        try {
            //get the index of the record that was selected
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            index = extras.getInt("USERINDEX");
            problemIndex = extras.getInt("ProblemIndex");
            title = extras.getString("PROBLEMTITLE");
            problemID = extras.getString("PROBLEMID");
            problemDescription = extras.getString("PROBLEMDESCRIPTION");
            problemDate = extras.getString("PROBLEMDATE");
        } catch (Exception e) {
            Log.d("RecordList", "onResume: Error getting record");
        }

        try {
            //set the patient username and problem title header
            TextView username_text = findViewById(R.id.patientUsername);
            username_text.setText(patients.get(index).getUsername().toString());
            TextView title_text = findViewById(R.id.recordTitleTextView);
            title_text.setText(title);
        } catch (Exception e) {
            Log.d("RecordList", "onResume: Error loading user data");
        }

        try {
            //fetch from elasticsearch and populate the records list
            //Records are queried by the current user's username
            ElasticSearchRecordController.GetRecordsTask getRecordsTask = new ElasticSearchRecordController.GetRecordsTask();
            getRecordsTask.execute(problemID);
            Log.i("AddRecordTask", "Username: " + patients.get(index).getUsername().toString());
            Log.i("AddRecordTask", "PROBLEMID:  " + problemID);
            List<Record> foundRecords = getRecordsTask.get();
            recordList.addAll(foundRecords);
            Log.i("AddRecordTask", String.valueOf(recordList.size()));
        } catch (Exception e) {
            Log.i("AddRecordTask", "Failed to get the records from the async object");
        }

        try {
            adapter.notifyDataSetChanged();
            //set the patient username and problem title header
            TextView username_text = findViewById(R.id.patientUsername);
            username_text.setText(patients.get(index).getUsername().toString());
            TextView title_text = findViewById(R.id.recordTitleTextView);
            title_text.setText(title);
        } catch (Exception e) {
            Log.d("AddRecordTask", "Failed to set patient username and problem title.");
        }
        //set the problem description, title, date and total record count
        TextView description_text = findViewById(R.id.userRecordDescription);
        description_text.setText(problemDescription);
        TextView recordCount = findViewById(R.id.userRecordCount);
        recordCount.setText(String.valueOf(recordList.size()));
        TextView date_text = findViewById(R.id.recordDate);
        date_text.setText(problemDate);

    }

    public void toAddRecordActivity(View view) {
        //Move to add record activity
        Intent intent = new Intent(RecordListActivity.this, AddRecordActivity.class);
        Bundle extras = new Bundle();
        extras.putString("PROBLEMID", problemID);
        extras.putInt("ProblemIndex", problemIndex);
        extras.putInt("USERINDEX", index);
        intent.putExtras(extras); //pass the patient username to the add record activity
        startActivity(intent);
    }
}
