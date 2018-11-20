package project.ece301.mantracker.CareProviderHome;

import java.util.ArrayList;

import project.ece301.mantracker.Account.Account;
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
        else {
            try {
                Patient patient = new Patient();
                patient.setUsername(new Username("rfurrer1"));
                patients.add(patient);
//                careProviderHomeView.update();
            } catch (Username.InvalidUsernameException e) {
            }
        }
    }

    public void onDestroy() {
        careProviderHomeView = null;
    }

    public int getPatientCount() {
        return (null != patients ? patients.size() : 0);
    }


    public void addPatient(String username) {
        Account account = dataManager.getUser(username);
        if (!(account==null)){
            Patient patient = (Patient) account;
            careProviderHomeView.showAddedPatientToast(username);
            patients.add(patient);
            careProviderHomeView.update();
        } else {
            careProviderHomeView.showNoPatientToast(username);
        }

    }

    public Patient getPatientAt(int i) {
        return patients.get(i);
    }
}
