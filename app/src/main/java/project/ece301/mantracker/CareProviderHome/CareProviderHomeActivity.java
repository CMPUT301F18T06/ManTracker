package project.ece301.mantracker.CareProviderHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import project.ece301.mantracker.R;
import project.ece301.mantracker.User.Patient;

public class CareProviderHomeActivity extends AppCompatActivity implements CareProviderHomeView {

    private CareProviderHomePresenter presenter;
    private PatientListAdapter adapter;
    private DrawerLayout drawerLayout;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.care_provider_home);

        presenter = new CareProviderHomePresenter(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        RecyclerView patientRecyclerView = findViewById(R.id.patient_list);
        adapter = new PatientListAdapter();
        patientRecyclerView.setAdapter(adapter);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchBar = findViewById(R.id.search_bar);
        findViewById(R.id.add_patient).setOnClickListener(v -> onOpenAddPatientDialog());
    }

    private void onOpenAddPatientDialog() {
        new AddPatientDialogFragment().show(getSupportFragmentManager(), "add_patient");
    }

    protected void addPatient(String username) {
        presenter.addPatient(username);
    }

    @Override
    public void showNoPatientToast(String username) {
        Toast.makeText(getApplicationContext(), getResources()
                        .getString(R.string.no_username_error) + ": " + username,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAddedPatientToast(String username) {
        Toast.makeText(getApplicationContext(), username + " " + getResources()
                .getString(R.string.added), Toast.LENGTH_LONG).show();
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
    public void navigateToPatient(Patient patient) { //TODO: pass whatever and go to different class
        Intent goToProblem = new Intent(this, CareProviderHomeActivity.class);
        startActivity(goToProblem);
        finish();
    }


    private class PatientListAdapter extends RecyclerView.Adapter<PatientViewHolder> {

        @Override
        @NonNull
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            CardView v = (CardView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.patient_card, viewGroup, false);
            return new PatientViewHolder(v); //Wrap it in a ViewHolder.
        }

        @Override
        public void onBindViewHolder(@NonNull final PatientViewHolder viewHolder, int i) {
            Patient patient = presenter.getPatientAt(i);
            viewHolder.setPatientNameText(patient.getUsername().getUserID());
            viewHolder.setPatientNumberOfProblemsText(presenter.getPatientCount());

            viewHolder.cardView.setOnClickListener(v -> navigateToPatient(patient));
        }

        @Override
        public int getItemCount() {
            return presenter.getPatientCount();
        }
    }

    private class PatientViewHolder extends RecyclerView.ViewHolder implements PatientCard {

        private CardView cardView;
        private TextView titleView;
        private TextView descriptionView;

        private PatientViewHolder(CardView card) {
            super(card);
            cardView = card;
            titleView = card.findViewById(R.id.patient_id);
            descriptionView = card.findViewById(R.id.num_problems);
        }

        @Override
        public void setPatientNameText(String patientName) {
            titleView.setText(patientName);
        }

        @Override
        public void setPatientNumberOfProblemsText(int numberOfProblems) {
            descriptionView.setText(String.valueOf(numberOfProblems));
        }
    }
}

