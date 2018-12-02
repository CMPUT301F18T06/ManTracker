package project.ece301.mantracker.CareProviderHome;

import android.util.Log;

import java.io.InvalidClassException;
import java.util.ArrayList;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;

public class CareProviderHomePresenter {
    private CareProviderHomeView careProviderHomeView;
    private DataManager dataManager;

    public CareProviderHomePresenter(CareProviderHomeView careProviderHomeView) {
        this.careProviderHomeView = careProviderHomeView;
        dataManager = DataManager.getInstance();
        careProviderHomeView.update();
    }

    public void onDestroy() {
        careProviderHomeView = null;
    }




    public void addPatient(String username) {
        Account account = dataManager.getUser(username);
        if (account instanceof Patient) {
            Patient patient = (Patient) account;
            careProviderHomeView.showAddedPatientToast(username);
            Log.d("ADD PATIENT", "before");
            Log.d("ADD PATIENT", account.getUsernameText());
//            dataManager.addUser(dataManager.getLoggedInUser());
            dataManager.addPatient(patient);
            Log.d("ADD PATIENT", "done");
            careProviderHomeView.update();
            Log.d("ADD PATIENT", "view updated");
        } else if (account instanceof CareProvider) {
            careProviderHomeView.showNoPatientToast(username);
        } else {
            careProviderHomeView.showNoPatientToast(username);
        }

    }

    public Patient getPatientAt(int i) {
        return dataManager.getPatientAt(i);
    }

    public int getPatientProblemCount(int i) {
        return dataManager.getPatientProblemCount(i);
    }

    public int getPatientCount() {
        return dataManager.getPatientCount();
    }
}
