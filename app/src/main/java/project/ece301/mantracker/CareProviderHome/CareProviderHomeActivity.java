package project.ece301.mantracker.CareProviderHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.R;

public class CareProviderHomeActivity extends AppCompatActivity implements CareProviderHomeView {

    private CareProviderHomePresenter presenter;
    private PatientListAdapter adapter;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.care_provider_home);

        presenter = new CareProviderHomePresenter(this);

        RecyclerView patientRecyclerView = findViewById(R.id.patient_list);
        adapter = new PatientListAdapter(getApplicationContext(), presenter);
//        adapter.setClickListener(this);
        patientRecyclerView.setAdapter(adapter);
        searchBar = findViewById(R.id.search_bar);
        findViewById(R.id.add_patient).setOnClickListener(v -> addPatient());
    }

    private void addPatient() {
        searchBar.setError("No search results");
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void update() {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void showNoSearchResults() {
        searchBar.setError("No search results");
    }

    @Override
    public void navigateToProblem(MedicalProblem problem) { //TODO: pass whatever and go to different class
        Intent goToProblem = new Intent(this, CareProviderHomeActivity.class);
        startActivity(goToProblem);
        finish();
    }
}

