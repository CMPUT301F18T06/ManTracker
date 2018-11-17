package project.ece301.mantracker.CareProviderHome;

import java.util.ArrayList;

import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.User.Patient;

public class CareProviderHomePresenter {
    private CareProviderHomeView careProviderHomeView;
    private ArrayList<Patient> patients;
    private DataManager dataManager;

    public CareProviderHomePresenter(CareProviderHomeView careProviderHomeView) {
        this.careProviderHomeView = careProviderHomeView;
        dataManager = DataManager.getInstance();
        patients = dataManager.getPatients();
        if (patients==null)
            patients = new ArrayList<>();
        Patient patient = new Patient(); //test
        Patient patient2 = new Patient();
        try {
            patient.setUsername(new Username("rfurrer1"));
            patient2.setUsername(new Username("thename8"));
        } catch (Username.InvalidUsernameException e) {
        } finally {
            patients.add(patient);
            patients.add(patient2);
        }
        careProviderHomeView.update();
    }

    public void onDestroy() {
        careProviderHomeView = null;
    }

    public int getPatientCount() {
        return (null != patients ? patients.size() : 0);
    }

    public String getPatientIdByPosition(int i) {
        return patients.get(i).getUsername().getUserID();
    }
}