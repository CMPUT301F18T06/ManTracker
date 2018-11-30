package project.ece301.mantracker.Activity;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Search;
import project.ece301.mantracker.MedicalProblem.ElasticSearchProblemController;
import project.ece301.mantracker.MedicalProblem.ElasticSearchRecordController;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;

import static project.ece301.mantracker.File.StoreData.patients;

public class SearchableActivity extends AppCompatActivity {
    /*This activity is what popups up the user when they execute a search
    * it will display all problems and/or records that match the indicated keywords*/
    private ListView recordListView;
    private ListView problemListView;
    private ArrayAdapter<Record> recordAdapter;
    private ArrayList<Record> recordList;
    private ArrayAdapter<MedicalProblem> problemAdapter;
    private ArrayList<MedicalProblem> problemList;
    private String query;
    private int patientindex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        //get intent and query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            Log.i("searchable", query);
        }


        problemListView = findViewById(R.id.problemResults);
        recordListView = findViewById(R.id.recordResults);
        //when the user wants to click on record
        recordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //when user selects a problem from the list
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //go to the patient record list
                Intent recordDetailsSwitch = new Intent(SearchableActivity.this, RecordDetailsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("RECORDID", recordList.get(position).getID());
                extras.putString("USERNAME", recordList.get(position).getAssociatedPatient());

                for(int i = 0; i < patients.size(); i ++)
                {   //get the index of the patient that matches the username
                    if(patients.get(i).getUsername().toString().equals(recordList.get(position)
                            .getAssociatedPatient())) {
                        patientindex = i;
                        break;
                    }
                }
                extras.putInt("USERINDEX", patientindex);

                recordDetailsSwitch.putExtras(extras);
                startActivity(recordDetailsSwitch);
            }
        });
        // if the user wants to click on problem
        problemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //when user selects a problem from the list
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //go to the patient record list
                Intent recordListSwitch = new Intent(SearchableActivity.this, RecordListActivity.class);
                Bundle extras = new Bundle();
                extras.putString("PROBLEMTITLE", problemList.get(position).getTitle());
                extras.putInt("ProblemIndex", position);
                extras.putString("PROBLEMID", problemList.get(position).getId());
                extras.putString("PROBLEMDESCRIPTION", problemList.get(position).getDescription());
                extras.putString("PROBLEMDATE", problemList.get(position).getDate());

                for(int i = 0; i < patients.size(); i ++)
                {   //get the index of the patient that matches the username
                    if(patients.get(i).getUsername().toString().equals(problemList.get(position)
                            .getPatientUsername())) {
                        patientindex = i;
                        break;
                    }
                }
                extras.putInt("USERINDEX", patientindex);

                recordListSwitch.putExtras(extras);
                startActivity(recordListSwitch);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        recordList = new ArrayList<Record>();
        //update the record listview
        recordAdapter = new ArrayAdapter<Record>(this, R.layout.problem_list_item, recordList);
        recordListView.setAdapter(recordAdapter);

        problemList = new ArrayList<MedicalProblem>();
        //update the problem listview
        problemAdapter = new ArrayAdapter<MedicalProblem>(this, R.layout.problem_list_item, problemList);
        problemListView.setAdapter(problemAdapter);

        //we have the query that the user is searching for so now we can search for all problems
        //and records that match the query

        //first query the records and populate the list
        //fetch from elasticsearch and populate the records list
        //Records are queried by the current user's username
        try {
            ElasticSearchRecordController.UserQuery getRecordsQuery = new ElasticSearchRecordController.UserQuery();
            getRecordsQuery.execute(query);
            List<Record> foundRecords = getRecordsQuery.get();
            Log.i("searchable", "returned results: " + String.valueOf(foundRecords.size()));
            recordList.addAll(foundRecords);
            Log.i("RecordQueryTask", String.valueOf(recordList.size()));
        } catch (Exception e) {
            Log.i("RecordQueryTask", "Failed");
        }

        try {
            ElasticSearchProblemController.UserQuery getProblemsQuery = new ElasticSearchProblemController.UserQuery();
            getProblemsQuery.execute(query);
            List<MedicalProblem> foundProblems = getProblemsQuery.get();
            problemList.addAll(foundProblems);
            Log.i("RecordQueryTask", String.valueOf(recordList.size()));
        } catch (Exception e) {
            Log.i("RecordQueryTask", "Failed");
        }

        recordAdapter.notifyDataSetChanged();
        problemAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
