package project.ece301.mantracker.PatientHome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import project.ece301.mantracker.R;

public class PatientHomeActivity extends AppCompatActivity implements PatientHomeView {

    private PatientHomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_home);

        presenter = new PatientHomePresenter(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}