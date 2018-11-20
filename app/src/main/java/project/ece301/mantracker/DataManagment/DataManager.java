package project.ece301.mantracker.DataManagment;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.MedicalProblem.ElasticSearchCareproviderContoller;
import project.ece301.mantracker.MedicalProblem.ElasticSearchPatientController;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class DataManager {
    private static DataManager instance;

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    public DataManager() {
    }

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

    public boolean addUser(Account account) {
        return true;
    }

    public ArrayList<Patient> getPatients() {
        return null;
    }

    public boolean addPatient(Patient patient) {
        return true;
    }

    public boolean deletePatient(Patient patient) {
        return true;
    }

    public ArrayList<MedicalProblem> getProblems(Patient patient) {
        return null;
    }

    public boolean addProblem(MedicalProblem problem) {
        return true;
    }

    public boolean deleteProblem(MedicalProblem problem) {
        return true;
    }

    public ArrayList<Record> getRecords(MedicalProblem problem) {
        return null;
    }

    public boolean addRecord(Record record) {
        return true;
    }

    public boolean deleteRecord(Record record) {
        return true;
    }
}
