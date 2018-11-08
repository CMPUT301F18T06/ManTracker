package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.R;

public class EditProfileActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;
    private Account account;

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

        this.usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        this.emailTextView = (TextView) findViewById(R.id.emailTextView);
        this.phoneTextView = (TextView) findViewById(R.id.phoneTextView);
    }

}
