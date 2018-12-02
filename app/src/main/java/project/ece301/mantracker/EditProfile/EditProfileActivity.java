package project.ece301.mantracker.EditProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.R;

public class EditProfileActivity extends AppCompatActivity implements EditProfileContract.View{

    private EditProfilePresenter mEditProfilePresenter;
    private String username;

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private Toolbar toolbar;

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
}
