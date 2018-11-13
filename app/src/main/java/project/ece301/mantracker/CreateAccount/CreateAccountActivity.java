package project.ece301.mantracker.CreateAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;

import project.ece301.mantracker.Activity.MainActivity;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView {

    private EditText username;
    private EditText email;
    private EditText phonenumber;
    private CheckBox isCareProvider;
    private CreateAccountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phone);
        isCareProvider = findViewById(R.id.cb_care_provider);
        findViewById(R.id.btn_confirm).setOnClickListener(v -> validateCredentials());

        presenter = new CreateAccountPresenter(this, new CreateAccountInteractor());
    }

    private void validateCredentials() {
        presenter.validateCredentials(username.getText().toString(), email.getText().toString(),
                phonenumber.getText().toString(), isCareProvider.isChecked());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showUsernameNotUniqueError() {
        username.setError(getString(R.string.username_taken_error));
    }

    @Override
    public void showUsernameInvalidError() {
        username.setError(getString(R.string.username_invalid_error));
    }

    @Override
    public void showEmailError() {
        email.setError(getString(R.string.email_invalid_error));
    }

    @Override
    public void showPhoneError() {
        phonenumber.setError(getString(R.string.phone_invalid_error));
    }

    @Override
    public void navigateToPatientHome(Patient patient) { //TODO: correct location
        Intent goToMain = new Intent(this, MainActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("user", patient);
        startActivity(goToMain);
        finish();
    }

    @Override
    public void navigateToCareProviderHome(CareProvider careProvider) { //TODO: correct location
        Intent goToMain = new Intent(this, MainActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("user", careProvider);
        startActivity(goToMain);
        finish();
    }
}
