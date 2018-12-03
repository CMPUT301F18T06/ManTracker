package project.ece301.mantracker.DataManagment;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.MedicalProblem.ElasticSearchCareproviderContoller;
import project.ece301.mantracker.MedicalProblem.ElasticSearchPatientController;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class ElasticSearchController {
    /**
     * Gets a user by their username as a string.
     * @param username the username as a string of the user to get
     * @return Returns the desired Account, if username is found on elastic search. Returns null if the username is not found.
     */
    public Account getUser(String username) {
        //use elastic search to search for user and return the account
        //user could be patient or care provider
        ArrayList<Patient> patient = new ArrayList<Patient>();
        ArrayList<CareProvider> careProvider = new ArrayList<CareProvider>();

        try {
            //fetch from elasticsearch and populate the records list
            //Records are queried by the current user's username
            ElasticSearchPatientController.GetPatientTask getPatientsTask = new ElasticSearchPatientController.GetPatientTask();
            getPatientsTask.execute(username);
            List<Patient> foundPatient = getPatientsTask.get();
            patient.addAll(foundPatient);
        } catch (Exception e) {
            Log.i("AddRecordTask", "Failed to get the records from the async object");
        }
        if (!patient.isEmpty()) {return patient.get(0);}

        try {
            //fetch from elasticsearch and populate the records list
            //Records are queried by the current user's username
            ElasticSearchCareproviderContoller.GetCareProviderTask getCareProvidersTask = new ElasticSearchCareproviderContoller.GetCareProviderTask();
            getCareProvidersTask.execute(username);
            List<CareProvider> foundCareProvider = getCareProvidersTask.get();
            careProvider.addAll(foundCareProvider);
        } catch (Exception e) {
            Log.i("GetProviderTask", "Failed to get the records from the async object");
        }
        if (!careProvider.isEmpty()) {return careProvider.get(0);}

        //if no careprovider or patient has user name then return null
        return null;
    }

    /**
     * Adds a user to elastic search
     * @param account the account to add to elastic search
     * @return True if successful. False otherwise.
     */
    public boolean addUser(Account account) {
        if (account instanceof CareProvider) {
            CareProvider careProvider = (CareProvider) account;
            //post to elasticsearch
            ElasticSearchCareproviderContoller.AddCareProviderTask addCareProviderTask = new ElasticSearchCareproviderContoller.AddCareProviderTask();
            addCareProviderTask.execute(careProvider);
            return true;
        } else if (account instanceof Patient) {
            Patient patient = (Patient) account;
            //post to elasticsearch
            ElasticSearchPatientController.AddPatientTask addPatientTask = new ElasticSearchPatientController.AddPatientTask();
            addPatientTask.execute(patient);
            return true;
        }
        return false;
    }
}
