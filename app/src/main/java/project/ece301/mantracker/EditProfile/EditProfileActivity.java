package project.ece301.mantracker.EditProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.R;

public class EditProfileActivity extends AppCompatActivity implements EditProfileContract.View{

    private EditProfilePresenter mEditProfilePresenter;
    private Account account;

    private TextView usernameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        this.usernameTextView = findViewById(R.id.usernameTextView);
        this.emailTextView = findViewById(R.id.emailTextView);
        this.phoneTextView = findViewById(R.id.phoneTextView);
    }

    /*
        Implement the methods provided by EditProfileContract.View
     */
    @Override
    public void updateUsername(Username username) {
        this.usernameTextView.setText(username.getUserID());
    }

    @Override
    public void updateEmail(Email email) {
        this.emailTextView.setText(email.getEmail());
    }

    @Override
    public void updatePhone(String phone) {
        this.phoneTextView.setText(phone);
    }
}
