package project.ece301.mantracker.CareProviderHome;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.support.v7.widget.CardView;

import project.ece301.mantracker.R;

class PatientCard extends RecyclerView.ViewHolder {
    CardView cardView;
    TextView patientId;

    PatientCard(CardView card) {
        super(card);
        cardView = card;
        patientId = card.findViewById(R.id.patient_id);
    }

    public void setPatientId(String patientName) {
        patientId.setText(patientName);
    }

    public void onStart() {

    }

}
