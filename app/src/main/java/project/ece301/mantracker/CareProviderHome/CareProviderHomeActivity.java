package project.ece301.mantracker.CareProviderHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.R;

public class CareProviderHomeActivity extends AppCompatActivity implements CareProviderHomeView {

    private CareProviderHomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.care_provider_home);

        presenter = new CareProviderHomePresenter(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void update() {

    }

    @Override
    public void showNoSearchResults() {

    }

    @Override
    public void navigateToProblem(MedicalProblem problem) {

    }
}