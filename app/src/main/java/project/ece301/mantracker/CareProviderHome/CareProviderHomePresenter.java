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
        if (account == null)
            careProviderHomeView.showNoPatientToast(username);
        else if (dataManager.getPatients().contains(account)) {
            careProviderHomeView.showAlreadyAddedPatientToast(username);
        } else if (account instanceof Patient) {
            dataManager.addPatient((Patient) account);
            careProviderHomeView.showAddedPatientToast(username);
            careProviderHomeView.update();
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
