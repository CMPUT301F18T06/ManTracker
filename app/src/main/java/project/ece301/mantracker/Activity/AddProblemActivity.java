package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;

public class AddProblemActivity extends AppCompatActivity {

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);

        Intent intent = getIntent();
        index = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));

        // set the username
        TextView heading_text = findViewById(R.id.userNameTextView2);
        heading_text.setText(patients.get(index).getUsername().toString());
    }

    public void Save_Click(View view){

        // set the username
        String problemTitle = ((EditText)findViewById(R.id.problem_title)).getText().toString();
        String problemDescription = ((EditText)findViewById(R.id.problem_description)).getText().toString();

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String date_formatted = date.format(new Date());

        // change the patient object
        Patient patient = patients.get(index);
        patient.addProblem(new MedicalProblem(problemDescription,problemTitle,date_formatted));
        patients.add(index,patient);

        // Saved
        finish();
    }
}