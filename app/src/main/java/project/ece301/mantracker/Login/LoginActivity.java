package project.ece301.mantracker.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import project.ece301.mantracker.Activity.MainActivity;
import project.ece301.mantracker.Activity.ProblemListActivity;
import project.ece301.mantracker.CareProviderHome.CareProviderHomeActivity;
import project.ece301.mantracker.CreateAccount.CreateAccountActivity;
import project.ece301.mantracker.File.StoreData;

import project.ece301.mantracker.MedicalProblem.ElasticSearchPatientController;

import project.ece301.mantracker.R;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText username;
    private LoginPresenter presenter;
    private EditText shortCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        StoreData.loadFromFile(this); //load local storage

        username = findViewById(R.id.username);
        shortCode = findViewById(R.id.shortCodeInput);
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
    public void navigateToCareProviderHome(CareProvider careProvider) { //TODO: pass in account
        Intent goToMain = new Intent(this, CareProviderHomeActivity.class);
        goToMain.putExtra("CAREPROVIDERINDEX", careProvider.getIndex());
        Log.i("CAREPROVIDERHOME", String.valueOf(careProvider.getIndex()));
        startActivity(goToMain);
        finish();
    }

    @Override
    public void navigateToPatientHome(Patient patient) {
        Intent goToMain = new Intent(this, ProblemListActivity.class);
        goToMain.putExtra("PATIENTINDEX", patient.getIndex());
        Log.i("PATIENTHOME", String.valueOf(patient.getIndex()));
        startActivity(goToMain);
        finish();
    }

    @Override
    public void navigateToCreateAccount() {
        Intent goToCreateAccount = new Intent(this, CreateAccountActivity.class);
        startActivity(goToCreateAccount);
        finish();
    }

    @Override
    public void onBackPressed() {
//        System.exit(0);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory( Intent.CATEGORY_HOME );
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void shortCodeEntered(View view) {
        //we are going to use elasticsearch to find all the patients and check if any of them match
        //the short code entered and then og in like normal
        ElasticSearchPatientController.GetPatientCodeTask getPatientTask = new ElasticSearchPatientController.GetPatientCodeTask();
        getPatientTask.execute(shortCode.getText().toString());
        List<Patient> foundPatient;
        try {
            foundPatient = getPatientTask.get();
            presenter.validateCredentials(foundPatient.get(0).getUsername().toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}