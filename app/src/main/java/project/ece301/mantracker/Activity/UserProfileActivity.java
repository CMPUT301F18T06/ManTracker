package project.ece301.mantracker.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.Arrays;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.EditProfile.EditProfileActivity;
import project.ece301.mantracker.File.StoreData;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;
/* This activity simply displays the user's username, phone and email*/

public class UserProfileActivity extends AppCompatActivity {
    private TextView username;
    private TextView email;
    private TextView phone;
    private TextView loginCode;
    private int index;
    private Toolbar toolbar;
    protected final int EDIT_REQUEST_CODE = 1;
    private DataManager dataManager;

    public static final String PROFILE_EDIT_INDEX = "PROFILE_EDIT_INDEX";

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
        loginCode = findViewById(R.id.LoginCode);

        //get the user index
        Intent intent = getIntent();
        index = intent.getIntExtra("USERINDEX", -1);
        dataManager = DataManager.getInstance(getApplicationContext());
    }

    @Override
    protected void onResume(){
        super.onResume();


        //set email, phone and username
        if (dataManager.getLoggedInUser() instanceof Patient || index < 0) {
            username.setText(dataManager.getLoggedInUser().getUsernameText());
            email.setText(dataManager.getLoggedInUser().getEmail().getEmail());
            phone.setText(dataManager.getLoggedInUser().getPhone());
            loginCode.setText(dataManager.getLoggedInUser().getShortCode());
        } else if (dataManager.getLoggedInUser() instanceof CareProvider) {
            username.setText(dataManager.getPatient(index).getUsername().toString());
            email.setText(dataManager.getPatient(index).getEmail().getEmail());
            phone.setText(dataManager.getPatient(index).getPhone());
            loginCode.setText(dataManager.getPatient(index).getShortCode());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_edit was selected
            case R.id.action_edit:
                Intent goToEdit = new Intent(this, EditProfileActivity.class);
                goToEdit.putExtra(PROFILE_EDIT_INDEX, dataManager.getPatient(index).getIndex());
                startActivityForResult(goToEdit, EDIT_REQUEST_CODE);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                index = data.getIntExtra(EditProfileActivity.NEW_INDEX, -1);
//                Log.d("EditProfile", "UserProfileActivity: User Shortcode: " +
//                        StoreData.patients.get(index).getShortCode());
                }
            if (resultCode == Activity.RESULT_CANCELED) {
                // do nothing
            }
        }
    }
}
