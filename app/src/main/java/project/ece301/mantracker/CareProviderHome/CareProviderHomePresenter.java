package project.ece301.mantracker.CareProviderHome;

import java.io.InvalidClassException;
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
        try {
            patients = dataManager.getPatients();
        } catch (InvalidClassException e) {
            e.printStackTrace(); // will never happen
            patients = new ArrayList<>();
        }
        careProviderHomeView.update();
    }

    public void onDestroy() {
        careProviderHomeView = null;
    }

    public int getPatientCount() {
        return (null != patients ? patients.size() : 0);
    }


    public void addPatient(String username) {
        Account account = dataManager.getUser(username);
        if (!(account == null)) {
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

    public int getPatientProblemCount(int index) {
        return (null != patients ? (null != patients.get(index).getAllProblems()
                ? patients.get(index).getAllProblems().size() : 0) : 0);
    }
}
