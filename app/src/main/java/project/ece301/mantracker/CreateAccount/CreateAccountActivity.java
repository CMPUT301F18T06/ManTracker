package project.ece301.mantracker.CreateAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import project.ece301.mantracker.Activity.MainActivity;
import project.ece301.mantracker.Activity.ProblemListActivity;
import project.ece301.mantracker.CareProviderHome.CareProviderHomeActivity;
import project.ece301.mantracker.File.StoreData;
import project.ece301.mantracker.MedicalProblem.ElasticSearchCareproviderContoller;
import project.ece301.mantracker.MedicalProblem.ElasticSearchPatientController;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;
import static project.ece301.mantracker.File.StoreData.saveInFile;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView {

    private EditText username;
    private EditText email;
    private EditText phonenumber;

    private CheckBox isCareProvider;
    private CreateAccountPresenter presenter;

    public static final String USERNAME_EXTRA = "USERNAME";

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
    public void navigateToPatientHome(Patient patient) {
        Intent goToMain = new Intent(this, ProblemListActivity.class);
        int index = StoreData.getIndexOf(patient);
        patient.setIndex(index); //set the local index for the patient
        Log.i("PATIENTHOME", "Patient index: " + String.valueOf(index));
        goToMain.putExtra("PATIENTINDEX", StoreData.getIndexOf(patient));


        saveInFile(getApplicationContext());

        startActivity(goToMain);
        finish();
    }

    @Override
    public void navigateToCareProviderHome(CareProvider careProvider) { //TODO: pass in account
        Intent goToMain = new Intent(this, CareProviderHomeActivity.class);
        int index = StoreData.getIndexOf(careProvider);
        careProvider.setIndex(index); //set the local index for the patient
        Log.i("CP_HOME", "CP index: " + String.valueOf(index));
        goToMain.putExtra("CP_INDEX", StoreData.getIndexOf(careProvider));


        startActivity(goToMain);
        finish();
    }
}
