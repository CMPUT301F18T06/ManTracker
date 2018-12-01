package project.ece301.mantracker.DataManagment;

import android.util.Log;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.MedicalProblem.ElasticSearchCareproviderContoller;
import project.ece301.mantracker.MedicalProblem.ElasticSearchPatientController;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;

public class DataManager {
    private static DataManager instance;

    private static Account loggedInUser;

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    public DataManager() {
    }

    public static void setLoggedInUser(Account loggedInUser) {
        Log.d("LOGIN", "Setting loggin session");
        DataManager.loggedInUser = loggedInUser;
    }

    public Account getLoggedInUser() {
        return loggedInUser;
    }

    public Account logIn(String username) {
        Account account = getUser(username);
        loggedInUser = account;
        return account;
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

    public ArrayList<Patient> getPatients() throws InvalidClassException {
        if (loggedInUser instanceof CareProvider)
            return ((CareProvider) loggedInUser).getPatientsList();
        throw new InvalidClassException("Must be a care provider to get patients!");
    }

    public boolean addPatient(Patient patient) {
        ((CareProvider)loggedInUser).addPatient(patient);
        updateStores();
        Log.d("UPDATE", "Careprovider added patient");
        return true;
    }

    private void updateStores() {
        addUser(loggedInUser);
    }

    public Patient getPatientAt(int i) {
        try {
            return getPatients().get(i);
        } catch (InvalidClassException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPatientProblemCount(int index) {
        return ((CareProvider)loggedInUser).getPatientProblemCount(index);
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

    public int getPatientCount() {
        return ((CareProvider)loggedInUser).getPatientCount();
    }
}
