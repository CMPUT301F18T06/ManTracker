package project.ece301.mantracker.EditProfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.File.StoreData;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.Patient;

public class EditProfileActivity extends AppCompatActivity implements EditProfileContract.View{

    private EditProfilePresenter mEditProfilePresenter;
    private String username;

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private Toolbar toolbar;

    public static final String NEW_INDEX = "NEW_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Set up toolbar
        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(getString(R.string.title_activity_edit_profile));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();

            }
        });

        // Get the text views.
        this.usernameEditText = findViewById(R.id.editUsername);
        this.emailEditText = findViewById(R.id.editEmail);
        this.phoneEditText= findViewById(R.id.editPhone);

        // Get the username
        Intent intent = getIntent();
        this.username = intent.getStringExtra("Username");

        // Set up the presenter
        mEditProfilePresenter = new EditProfilePresenter(DataManager.getInstance(),
                this, this.username);
    }

    /*
        Implement the methods provided by EditProfileContract.View
     */
    @Override
    public void showUsername(Username username) {
        this.usernameEditText.setText(username.getUserID());
    }

    @Override
    public void showEmail(Email email) {
        this.emailEditText.setText(email.getEmail());
    }

    @Override
    public void showPhone(String phone) {
        this.phoneEditText.setText(phone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_save:
                if (mEditProfilePresenter.saveUser(usernameEditText.getText().toString(),
                        emailEditText.getText().toString(), phoneEditText.getText().toString())) {
                    Intent saveIntent = new Intent();
                    saveIntent.putExtra(NEW_INDEX, mEditProfilePresenter.getUserIndex());
                    setResult(Activity.RESULT_OK, saveIntent);
                    finish();
                }

                break;
            default:
                break;
        }
        return true;
    }
}
