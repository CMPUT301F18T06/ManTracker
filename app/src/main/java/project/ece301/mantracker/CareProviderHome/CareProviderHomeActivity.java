package project.ece301.mantracker.CareProviderHome;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import io.searchbox.core.Search;
import project.ece301.mantracker.Activity.ProblemListActivity;
import project.ece301.mantracker.Activity.SearchableActivity;
import project.ece301.mantracker.Activity.UserProfileActivity;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.EditProfile.EditProfileActivity;
import project.ece301.mantracker.File.StoreData;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;

public class CareProviderHomeActivity extends AppCompatActivity implements CareProviderHomeView {

    private CareProviderHomePresenter presenter;
    private PatientListAdapter adapter;
    private DrawerLayout drawerLayout;
    private SearchView searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.care_provider_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        RecyclerView patientRecyclerView = findViewById(R.id.patient_list);
        adapter = new PatientListAdapter();
        patientRecyclerView.setAdapter(adapter);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new CareProviderHomePresenter(this);
        //configure the search bar developer.android.com/guide/topics/search/search-dialog#java
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchBar = findViewById(R.id.search_bar);
        searchBar.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this,
                SearchableActivity.class)));
        findViewById(R.id.add_patient).setOnClickListener(v -> onOpenAddPatientDialog());
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = DataManager.getInstance().
                getLoggedInUser().getUsernameText();

        ((Button) findViewById(R.id.userNameTextView)).setText(name);
        (findViewById(R.id.userNameTextView)).setOnClickListener(view -> navagateToProfile(name));
    }

    private void onOpenAddPatientDialog() {
        new AddPatientDialogFragment().show(getSupportFragmentManager(), "add_patient");
    }

    private void navagateToProfile(String username) {
        Intent goToProfile = new Intent(this, UserProfileActivity.class);
        goToProfile.putExtra("Username", username);
        startActivity(goToProfile);
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
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNoSearchResults() {

    }

    @Override
    public void navigateToPatient(Patient patient) { //TODO: where to?
        Intent goToProblems = new Intent(this, ProblemListActivity.class);
        goToProblems.putExtra("PATIENTINDEX", StoreData.getIndexOf(patient));
        startActivity(goToProblems);
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
            viewHolder.setPatientNameText(patient.getUsernameText());
            viewHolder.setPatientNumberOfProblemsText(presenter.getPatientProblemCount(i));

            viewHolder.cardView.setOnClickListener(v -> navigateToPatient(patient));
            viewHolder.getTitleView().setOnClickListener(view -> navagateToProfile(patient.getUsernameText()));
        }

        @Override
        public int getItemCount() {
            return presenter.getPatientCount();
        }
    }

    private class PatientViewHolder extends RecyclerView.ViewHolder implements PatientCard {

        private CardView cardView;
        private Button titleView;
        private TextView descriptionView;

        private PatientViewHolder(CardView card) {
            super(card);
            cardView = card;
            titleView = card.findViewById(R.id.patient_id);
            descriptionView = card.findViewById(R.id.num_problems);
        }

        public Button getTitleView() {
            return titleView;
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

