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

import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.R;

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
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //go to the patient record list
                Intent recordListSwitch = new Intent(ProblemListActivity.this, RecordListActivity.class);
                Bundle extras = new Bundle();
                extras.putString("PROBLEMTITLE", problems.get(position).getTitle());
                extras.putInt("USERINDEX", index);
                recordListSwitch.putExtras(extras);
                startActivity(recordListSwitch);
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Intent intent = getIntent();
        index = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));

        // set the username
        TextView heading_text = findViewById(R.id.userNameTextView);
        heading_text.setText(patients.get(index).getUsername().toString());

        problems = new ArrayList<MedicalProblem>();
        populateUserProblems(index);
        adapter = new ArrayAdapter<MedicalProblem>(this,
                R.layout.problem_list_item, problems);
        oldProblems.setAdapter(adapter);
    }

    private void populateUserProblems(int index){
        ArrayList<MedicalProblem> med_problem = new ArrayList<MedicalProblem>();
        med_problem=patients.get(index).getAllProblems();

        for(int i=0;i<med_problem.size();i++){
            problems.add(med_problem.get(i));
        }
    }

    public void AddProblem(View view){
        Intent intent = getIntent();
        int index = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));

        Intent addProblemSwitch = new Intent(ProblemListActivity.this, AddProblemActivity.class);
        addProblemSwitch.putExtra(EXTRA_MESSAGE,Integer.toString(index));
        startActivity(addProblemSwitch);
    }


}
