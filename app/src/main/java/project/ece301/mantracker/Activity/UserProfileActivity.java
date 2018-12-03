package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import project.ece301.mantracker.R;
import static project.ece301.mantracker.File.StoreData.patients;
/* This activity simply displays the user's username, phone and email*/

public class UserProfileActivity extends AppCompatActivity {
    private TextView username;
    private TextView email;
    private TextView phone;
    private TextView loginCode;
    private int patientindex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_information);

        //set the views
        username = findViewById(R.id.userProfileUsername);
        email = findViewById(R.id.userProfileEmail);
        phone = findViewById(R.id.userProfilePhone);
        loginCode = findViewById(R.id.LoginCode);
    }

    @Override
    protected void onStart(){
        super.onStart();

        //get the patient index
        Intent intent = getIntent();
        patientindex = intent.getIntExtra("USERINDEX", 0);

        //set email, phone and username
        username.setText(patients.get(patientindex).getUsername().toString());
        email.setText(patients.get(patientindex).getEmail().getEmail());
        phone.setText(patients.get(patientindex).getPhone());
        loginCode.setText(patients.get(patientindex).getShortCode());
    }
}
