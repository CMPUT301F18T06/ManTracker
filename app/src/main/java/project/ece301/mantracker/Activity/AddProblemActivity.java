/**
 * Class Name: AddProblemActivity
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Activity for adding a problem. This activity allows the user to add and save a new problem.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.MedicalProblem.ElasticSearchProblemController;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;
import static project.ece301.mantracker.File.StoreData.saveInFile;

public class AddProblemActivity extends AppCompatActivity {
    private static String TAG = "AddProblemActivity";

    int index;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);
        dataManager = DataManager.getInstance(getApplicationContext());

        Intent intent = getIntent();
        index = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
        Log.d(TAG, String.valueOf(index));

        // set the username
        TextView heading_text = findViewById(R.id.userNameTextView2);
        heading_text.setText(patients.get(index).getUsername().toString());
    }

    /**
     * Handles the user clicking the save button.
     * Adds a problem to the patient object based on the fields the
     * user inputted in the UI and posts the new problem on elasticsearch.
     * @param view the save button object on the UI
     */
    public void Save_Click(View view){

        // set the username
        String problemTitle = ((EditText)findViewById(R.id.problem_title)).getText().toString();
        String problemDescription = ((EditText)findViewById(R.id.problem_description)).getText().toString();

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String date_formatted = date.format(new Date());

        // change the patient object
        Patient patient = patients.get(index);
        String patientUsername = patients.get(index).getUsername().toString();
        MedicalProblem problem = new MedicalProblem(problemDescription,problemTitle,date_formatted, patient.getID(), patientUsername);
        patient.addProblem(problem);
        patients.set(index,patient);
        dataManager.setLoggedInUser(patient);

        saveInFile(this); //save locally

        //post to elasticsearch
        try {
            ElasticSearchProblemController.AddProblemTask addProblemsTask = new ElasticSearchProblemController.AddProblemTask();
            addProblemsTask.execute(problem);
        } catch (Exception e) {
            Toast.makeText(this, "No Internet Connectivity", Toast.LENGTH_LONG).show();
        }

        //wait a few seconds for es to upload
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {

        }

        // Saved
        finish();
    }
}
