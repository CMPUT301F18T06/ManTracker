package project.ece301.mantracker.CareProviderHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import project.ece301.mantracker.Activity.MainActivity;
import project.ece301.mantracker.CreateAccount.CreateAccountActivity;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.CareProvider;

public class CareProviderHomeActivity extends AppCompatActivity implements CareProviderHomeView {

    private CareProviderHomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        presenter = new CareProviderHomePresenter(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}