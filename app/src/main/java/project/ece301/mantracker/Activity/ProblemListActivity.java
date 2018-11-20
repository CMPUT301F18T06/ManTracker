package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import project.ece301.mantracker.CreateAccount.CreateAccountActivity;
import project.ece301.mantracker.MedicalProblem.ElasticSearchPatientController;
import project.ece301.mantracker.MedicalProblem.ElasticSearchProblemController;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;

public class ProblemListActivity extends AppCompatActivity {

    private ArrayList<MedicalProblem> problems;
    private ListView oldProblems;
    private ArrayAdapter<MedicalProblem> adapter;
    public static final String EXTRA_MESSAGE = "com.example.aman.aanand_feelsbook.MESSAGE";
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);
        oldProblems = (ListView) findViewById(R.id.problem_list);

        oldProblems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //when user selects a problem from the list
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //go to the patient record list
                Intent recordListSwitch = new Intent(ProblemListActivity.this, RecordListActivity.class);
                Bundle extras = new Bundle();
                extras.putString("PROBLEMTITLE", problems.get(position).getTitle());
                extras.putInt("USERINDEX", index);
                extras.putInt("ProblemIndex", position);
                extras.putString("PROBLEMID", problems.get(position).getId());
                extras.putString("PROBLEMDESCRIPTION", problems.get(position).getDescription());
                extras.putString("PROBLEMDATE", problems.get(position).getDate());
                recordListSwitch.putExtras(extras);
                startActivity(recordListSwitch);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        index = intent.getIntExtra("PATIENTINDEX", -1);
        Log.i("PatientQuery", "index: " + index);

        // set the username
        TextView heading_text = findViewById(R.id.userNameTextView);

        heading_text.setText(patients.get(index).getUsername().toString());
        Log.i("AddProblemTask", "index: "+ String.valueOf(index));

        problems = new ArrayList<MedicalProblem>();
        adapter = new ArrayAdapter<MedicalProblem>(this,
                R.layout.problem_list_item, problems);
        populateUserProblems(index);
        oldProblems.setAdapter(adapter);
    }

    private void populateUserProblems(int index){

        try {
          //try to get from elasticsearch first. If not able, grab locally
            ElasticSearchProblemController.GetProblemsTask getProblemsTask = new ElasticSearchProblemController.GetProblemsTask();
            getProblemsTask.execute(patients.get(index).getID());

            try {
                List<MedicalProblem> foundProblems = getProblemsTask.get();
                problems.addAll(foundProblems);
                Log.i("ELASTICSEARCH", "WORKS SUCCESSFULLY FOR PROBLEMs");

            } catch (Exception e) {
                Log.i("AddProblemTask", "Failed to get the records from the async object");
            }
            adapter.notifyDataSetChanged();
            Log.i("PATIENTHOME", "NOTIFIED");
        } catch (Exception e) {
            ArrayList<MedicalProblem> med_problem = new ArrayList<MedicalProblem>();
            med_problem=patients.get(index).getAllProblems();
            Log.i("PATIENTHOME", "SHOULDNOTREACH");
            for(int i=0;i<med_problem.size();i++){
                problems.add(med_problem.get(i));
            }
        }
    }

    public void AddProblem(View view){
        Intent addProblemSwitch = new Intent(ProblemListActivity.this, AddProblemActivity.class);
        addProblemSwitch.putExtra(EXTRA_MESSAGE,Integer.toString(index));
        startActivity(addProblemSwitch);
    }


}
