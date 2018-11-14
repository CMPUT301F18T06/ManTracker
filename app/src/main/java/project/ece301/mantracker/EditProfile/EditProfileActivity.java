package project.ece301.mantracker.EditProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.R;

public class EditProfileActivity extends AppCompatActivity implements EditProfileContract.View{

    private EditProfilePresenter mEditProfilePresenter;
    private String username;

    private TextView usernameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Get the text views.
        this.usernameTextView = findViewById(R.id.usernameTextView);
        this.emailTextView = findViewById(R.id.emailTextView);
        this.phoneTextView = findViewById(R.id.phoneTextView);

        // Get the username
        Intent intent = getIntent();
        this.username = intent.getStringExtra("Username");

        // Set up the presenter
        mEditProfilePresenter = new EditProfilePresenter(DataManager.getInstance(),
                this, this.username);
    }

    public String getUsername() {
        return this.username;
    }

    /*
        Implement the methods provided by EditProfileContract.View
     */
    @Override
    public void showUsername(String username) {
        this.usernameTextView.setText(username);
    }

    @Override
    public void showEmail(String email) {
        this.emailTextView.setText(email);
    }

    @Override
    public void showPhone(String phone) {
        this.phoneTextView.setText(phone);
    }
}
