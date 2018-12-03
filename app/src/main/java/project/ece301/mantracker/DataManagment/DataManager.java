/**
 * Class Name: DataManager
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * This class handles the retrieval of data using elastic search.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

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

/**
 * Class for retrieving data from ElasticSearch server
 *
 * @version 1.0
 * @since 1.0
 */
public class DataManager {
    private static DataManager instance;

    private static Account loggedInUser;

    /**
     * Returns an instance of DataManager.
     * @return an instance of DataManager.
     */
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

    /**
     * Gets a list a patients from elastic search
     * @return a list of patients
     */
    public ArrayList<Patient> getPatients() throws InvalidClassException {
        if (loggedInUser instanceof CareProvider)
            return ((CareProvider) loggedInUser).getPatientsList();
        throw new InvalidClassException("Must be a care provider to get patients!");
    }

    /**
     * Adds a patient to Elastic Search
     * @param patient the patient to add
     * @return True if successful. False otherwise.
     */
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

    /**
     * Deletes a patient from elastic search.
     * @param patient The patient to delete
     * @return True if successful. False otherwise.
     */
    public boolean deletePatient(Patient patient) {
        return true;
    }

    /**
     * Gets a list of problems from elastic search
     * @param patient the patient to get problems from
     * @return An ArrayList of MedicalProblems
     */
    public ArrayList<MedicalProblem> getProblems(Patient patient) {
        return null;
    }

    /**
     * Adds a MedicalProblem to elastic search
     * @param problem the Problem to add
     * @return True if successful. False otherwise.
     */
    public boolean addProblem(MedicalProblem problem) {
        return true;
    }

    /**
     * Deletes a problem from elastic search
     * @param problem the problem to delete
     * @return True if successful. False otherwise.
     */
    public boolean deleteProblem(MedicalProblem problem) {
        return true;
    }

    /**
     * Gets a list of records from elastic search
     * @param problem the problem associated with the records
     * @return An ArrayList of Records
     */
    public ArrayList<Record> getRecords(MedicalProblem problem) {
        return null;
    }

    /**
     * Adds a record to elastic search
     * @param record the record to add
     * @return True if successful. False otherwise.
     */
    public boolean addRecord(Record record) {
        return true;
    }

    /**
     * Deletes a record from elastic search.
     * @param record the record to delete
     * @return True if successful. False otherwise.
     */
    public boolean deleteRecord(Record record) {
        return true;
    }

    public int getPatientCount() {
        return ((CareProvider)loggedInUser).getPatientCount();
    }
}
