package project.ece301.mantracker.PatientHome;


public class PatientHomePresenter {
    private PatientHomeView patientHomeView;

    public PatientHomePresenter(PatientHomeView patientHomeView) {
        this.patientHomeView = patientHomeView;
    }

    public void onDestroy() {
        patientHomeView = null;
    }
}