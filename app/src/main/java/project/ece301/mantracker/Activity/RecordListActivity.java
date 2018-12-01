package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import project.ece301.mantracker.MedicalProblem.ElasticSearchRecordController;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;
import project.ece301.mantracker.Edit_MedPro.edit_medpro;

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
                extras.putString("USERNAME", recordList.get(position).getAssociatedPatient());

                extras.putInt("USERINDEX", index); // offline patient index
                extras.putInt("ProblemIndex", problemIndex); // offline problem index
                extras.putInt("RECORDINDEX", position); // offline record index


                recordDetailsSwitch.putExtras(extras);
                startActivity(recordDetailsSwitch);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.medpro_toolbar, menu);
        return true;
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
            problemIndex = extras.getInt("ProblemIndex");
            title = extras.getString("PROBLEMTITLE");
            problemID = extras.getString("PROBLEMID");
            problemDescription = extras.getString("PROBLEMDESCRIPTION");
            problemDate = extras.getString("PROBLEMDATE");
            index = extras.getInt("USERINDEX");

        } catch (Exception e) {
            Log.d("RecordList", "onResume: Error getting record");
        }

        try {
            //set the patient username and problem title header
            TextView username_text = findViewById(R.id.addNewRecordHeader);
            username_text.setText(patients.get(index).getUsername().toString());
            Toolbar medpro_toolbar = (Toolbar)findViewById(R.id.medpro_toolbar);
            setMedproToolbar(medpro_toolbar,title,problemDescription);
//            TextView title_text = findViewById(R.id.recordTitleTextView);
//            title_text.setText(title);
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
            TextView username_text = findViewById(R.id.addNewRecordHeader);
            username_text.setText(patients.get(index).getUsername().toString());
            Toolbar medpro_toolbar = (Toolbar)findViewById(R.id.medpro_toolbar);
            setMedproToolbar(medpro_toolbar,title,problemDescription);
//            TextView title_text = findViewById(R.id.recordTitleTextView);
//            title_text.setText(title);
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
        //
        extras.putInt("ProblemIndex", problemIndex);
        extras.putInt("USERINDEX", index);
        intent.putExtras(extras); //pass the patient username to the add record activity
        startActivity(intent);
    }


    public void setMedproToolbar(Toolbar toolbar,String title,String prodescription){

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.medpro_action_gallery:
                        Toast.makeText(RecordListActivity.this, "Gallery", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RecordListActivity.this,img_slideshow.class);
                        Bundle extras = new Bundle();
                        extras.putInt("USERINDEX",index);
                        extras.putInt("ProblemIndex",problemIndex);
                        intent.putExtras(extras);
                        startActivity(intent);

                        break;
                    case R.id.medpro_action_delete:
                        Toast.makeText(RecordListActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.medpro_action_edit:
                        Intent goEditproActivity = new Intent( RecordListActivity.this,  edit_medpro.class);
                        goEditproActivity.putExtra("PROTITLE", title);
                        goEditproActivity.putExtra("PRODESCRI", prodescription);
                        startActivity(goEditproActivity);

                        Toast.makeText(RecordListActivity.this, "Edit", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });







    }





}


