package project.ece301.mantracker.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import project.ece301.mantracker.Activity.MainActivity;
import project.ece301.mantracker.CareProviderHome.CareProviderHomeActivity;
import project.ece301.mantracker.CreateAccount.CreateAccountActivity;
import project.ece301.mantracker.PatientHome.PatientHomeActivity;
import project.ece301.mantracker.R;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText username;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        findViewById(R.id.btn_confirm).setOnClickListener(v -> validateCredentials());

        findViewById(R.id.create_account).setOnClickListener(v -> navigateToCreateAccount());
      
        presenter = new LoginPresenter(this, new LoginInteractor());
    }

    private void validateCredentials() {
        presenter.validateCredentials(username.getText().toString());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showUsernameInvalidError() {
        username.setError(getString(R.string.username_invalid_error));
    }

    @Override
    public void navigateToCareProviderHome() { //TODO: pass in account
        Intent goToMain = new Intent(this, CareProviderHomeActivity.class);
        startActivity(goToMain);
        finish();
    }

    @Override
    public void navigateToPatientHome() { //TODO: pass in account
        Intent goToMain = new Intent(this, PatientHomeActivity.class);
        startActivity(goToMain);
        finish();
    }

    @Override
    public void navigateToCreateAccount() {
        Intent goToCreateAccount = new Intent(this, CreateAccountActivity.class);
        startActivity(goToCreateAccount);
        finish();
    }
}