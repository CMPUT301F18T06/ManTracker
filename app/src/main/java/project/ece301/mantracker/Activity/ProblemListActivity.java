package project.ece301.mantracker.Activity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import project.ece301.mantracker.CreateAccount.CreateAccountActivity;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.DataManagment.LocalStorage;
import project.ece301.mantracker.Login.LoginActivity;
import project.ece301.mantracker.MedicalProblem.ElasticSearchPatientController;
import project.ece301.mantracker.MedicalProblem.ElasticSearchProblemController;
import project.ece301.mantracker.MedicalProblem.ElasticSearchRecordController;
import project.ece301.mantracker.MedicalProblem.Geolocation;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.Observer;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;

public class ProblemListActivity extends AppCompatActivity implements Observer {

    private ListView oldProblems;
    private ArrayAdapter<MedicalProblem> adapter;
    public static final String EXTRA_MESSAGE = "com.example.aman.aanand_feelsbook.MESSAGE";
    private int index;
    private int actualIndex;
    private SearchView searchBar;
    private TextView heading_text;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);
        oldProblems = (ListView) findViewById(R.id.problem_list);
        heading_text = findViewById(R.id.userNameTextView);
        findViewById(R.id.mapBTN).setOnClickListener(view -> goToGoogleMapsPlaces());
        if (DataManager.getInstance(getApplicationContext()).getLoggedInUser() instanceof CareProvider) {
            ((TextView) findViewById(R.id.goodDayTextView)).setText(R.string.veiwing);
            (findViewById(R.id.floatingActionButton)).setVisibility(View.GONE);
            (findViewById(R.id.logout)).setVisibility(View.GONE);
        }

        //configure the search bar developer.android.com/guide/topics/search/search-dialog#java
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchBar = findViewById(R.id.problemListSearch);
        searchBar.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this,
                SearchableActivity.class)));

        oldProblems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //when user selects a problem from the list
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //go to the patient record list
                Log.d("Click", String.valueOf(position));
                Log.d("Click", dataManager.getProblems(actualIndex).toString());
                Intent recordListSwitch = new Intent(ProblemListActivity.this, RecordListActivity.class);
                Bundle extras = new Bundle();
                extras.putString("PROBLEMTITLE", dataManager.getProblems(actualIndex).get(position).getTitle());
                extras.putInt("USERINDEX", index); // offline patient index
                extras.putInt("ACTUALUSERINDEX", actualIndex); // session patient index
                extras.putInt("ProblemIndex", position); // offline problem ID
                extras.putInt("ActualProblemIndex", position); // offline problem ID
                extras.putString("PROBLEMID",dataManager.getProblems(actualIndex).get(position).getId());
                extras.putString("PROBLEMDESCRIPTION", dataManager.getProblems(actualIndex).get(position).getDescription());
                extras.putString("PROBLEMDATE", dataManager.getProblems(actualIndex).get(position).getDate());
                recordListSwitch.putExtras(extras);
                startActivity(recordListSwitch);
            }
        });

        Intent intent = getIntent();
        index = intent.getIntExtra("PATIENTINDEX", -1);
        actualIndex = intent.getIntExtra("PATIENTINDEX", -1);
        dataManager = DataManager.getInstance(getApplicationContext());
        adapter = new ArrayAdapter<MedicalProblem>(this,
                R.layout.problem_list_item, dataManager.getProblems(actualIndex));
        oldProblems.setAdapter(adapter);
        populateUserProblems(index);
        patients.addAll(dataManager.getPatients());
        update();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // set the username

        heading_text.setText(dataManager.getPatient(index).getUsername().toString());
        Log.i("AddProblemTask", "index: "+ String.valueOf(index));

        adapter = new ArrayAdapter<MedicalProblem>(this,
                R.layout.problem_list_item, dataManager.getProblems(actualIndex));
        oldProblems.setAdapter(adapter);
        populateUserProblems(index);
        patients.addAll(dataManager.getPatients());
        update();
    }

    /**
     * Updates problems from ES. Should not be here
     * @param index
     */
    private void populateUserProblems(int index){

        try {
          //try to get from elasticsearch first. If not able, grab locally
            ElasticSearchProblemController.GetProblemsTask getProblemsTask = new ElasticSearchProblemController.GetProblemsTask();
            getProblemsTask.execute(dataManager.getPatient(index).getID());
            Log.d("PATIENT", dataManager.getPatient(index).getID());

            try {
                List<MedicalProblem> foundProblems = getProblemsTask.get();
                Log.i("ELASTICSEARCH", "WORKS SUCCESSFULLY FOR PROBLEMs");
                dataManager.setProblems(index, foundProblems);
                Log.i("ELASTICSEARCH", foundProblems.toString());
                LocalStorage.saveLoginSession(getApplicationContext(), dataManager.getLoggedInUser());
                update();

            } catch (Exception e) {
                Log.i("AddProblemTask", "Failed to get the records from the async object");
            }
            Log.i("PATIENTHOME", "NOTIFIED");
        } catch (Exception e) {
//            //What is med_problem for? None longer matters as already loaded in dataManager
//            ArrayList<MedicalProblem> med_problem = new ArrayList<MedicalProblem>();
//            med_problem=patients.get(index).getAllProblems();
//            Log.i("PATIENTHOME", "SHOULDNOTREACH");
//            for(int i=0;i<med_problem.size();i++){
//                problems.add(med_problem.get(i));
//            }
        }
    }

    public void AddProblem(View view){
        Intent addProblemSwitch = new Intent(ProblemListActivity.this, AddProblemActivity.class);
        addProblemSwitch.putExtra(EXTRA_MESSAGE,Integer.toString(index));
        startActivity(addProblemSwitch);
        update();
    }

    public void toUserProfile(View view) {
        //send the patient information to the user profile activity
        Intent userProfileIntent = new Intent(ProblemListActivity.this, UserProfileActivity.class );
        userProfileIntent.putExtra("USERINDEX", index);
        userProfileIntent.putExtra("ActualIndex", actualIndex);
        startActivity(userProfileIntent);
    }


    public void LogOut(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        DataManager.getInstance(getApplicationContext()).setLoggedInUser(null);
        intent.putExtra("LOGOUT","0");
        startActivity(intent);
        finish();
    }

    @Override
    public void update() {
        adapter.notifyDataSetChanged();
    }

    public ArrayList<LatLng> getAllLocations(){
        ArrayList<LatLng> geolocations = new ArrayList<>();
        for (MedicalProblem problem: problems){
            try {
                ArrayList<Record> recordList = new ArrayList<>();
                //fetch from elasticsearch and populate the records list
                //Records are queried by the current user's username
                ElasticSearchRecordController.GetRecordsTask getRecordsTask = new ElasticSearchRecordController.GetRecordsTask();
                getRecordsTask.execute(problem.getId());
                List<Record> foundRecords = getRecordsTask.get();
                recordList.addAll(foundRecords);

                for (Record record: recordList) {
                    if (record.getGeoLocation() != null)
                        geolocations.add(record.getGeoLocation().getLatLng());
                }
            } catch (Exception e) {
                Log.i("AddRecordTask", "Failed to get the records from the async object");
            }
        }
        return geolocations;
    }

    public void goToGoogleMapsPlaces() {
        Intent viewMap = new Intent(this, MapViewActivity.class);
        viewMap.putExtra("LATLNGS", getAllLocations());
        startActivity(viewMap);
    }
}
