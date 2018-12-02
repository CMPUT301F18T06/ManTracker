package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import project.ece301.mantracker.R;
import static project.ece301.mantracker.File.StoreData.patients;
/* This activity simply displays the user's username, phone and email*/

public class UserProfileActivity extends AppCompatActivity {
    private TextView username;
    private TextView email;
    private TextView phone;
    private int patientindex;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_information);

        // Set up toolbar
        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(getString(R.string.title_activity_edit_profile));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //set the views
        username = findViewById(R.id.userProfileUsername);
        email = findViewById(R.id.userProfileEmail);
        phone = findViewById(R.id.userProfilePhone);
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
        
    }
}
