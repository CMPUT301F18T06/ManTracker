/**
 * Class Name: StoreData
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Class used to access and save Patient data locally.
 * This class has an array list of patients. This array list gets updated when loading patients.
 * To get a locally stored patient, first use the loadFromFile method to load the patients array list,
 * then get the desired patient from the array list.
 * To save a new patient, add a patient to the patient array list, then use the saveInFile method.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.File;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;
// TODO: covert to Saving Accounts -- see rfurrer Feelsbook

/**
 * Class used to access and save Patient data locally.
 * This class has an array list of patients. This array list gets updated when loading patients.
 * To get a locally stored patient, first use the loadFromFile method to load the patients array list,
 * then get the desired patient from the array list.
 * To save a new patient, add a patient to the patient array list, then use the saveInFile method.
 *
 * @version 1.0
 * @see Patient
 * @since 1.0
 */
public class StoreData {

    public static final String FILENAME = "ManTracker.sav";
    public static final String CP_FILENAME = "ManTrackerCareProvidors.sav";
    public static ArrayList<Patient> patients = new ArrayList<Patient>();
    public static ArrayList<CareProvider> careProviders = new ArrayList<CareProvider>();

    /* loads the objects from the file to the patient list */

    /**
     * Loads all locally stored patients into the patients arraylist.
     * @param context the app's current context
     */
    public static void loadFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Patient>>(){}.getType();
            patients = gson.fromJson(reader, listType);
        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            patients = new ArrayList<Patient>();
        }
    }

    public static void loadCareProvidersFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(CP_FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<CareProvider>>(){}.getType();
            careProviders = gson.fromJson(reader, listType);
        }catch (FileNotFoundException e) {
            careProviders = new ArrayList<CareProvider>();
        }
    }

    /* saves the object from the list in the file */

    /**
     * Saves the patients arraylist locally.
     * @param context the app's current context
     */
    public static void saveInFile(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson =new Gson();
            gson.toJson(patients ,writer);

            writer.flush();
            fos.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
  
    public static void saveCareProvidersInFile(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(CP_FILENAME,0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson =new Gson();
            gson.toJson(careProviders ,writer);

            writer.flush();
            fos.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static int getIndexOf(Account account) {
        if (account instanceof CareProvider)
            return getIndexOfCareProvider((CareProvider) account);
        if (account instanceof Patient)
            return getIndexOfPatient((Patient) account);
        throw new IllegalArgumentException();
    }

    public static int getCareProviderIndex(String username) {
        for (CareProvider careProvider: careProviders) {
            if (careProvider.getUsernameText().equals(username))
                return careProviders.indexOf(careProvider);
        }
        return -1;
    }

    public static void addCareProvider(CareProvider careProvider, Context context) {
        int index = getCareProviderIndex(careProvider.getUsernameText());
        if (index >= 0)
            careProviders.set(index, careProvider);
        else
            careProviders.add(careProvider);
        saveCareProvidersInFile(context);
    }

    /**
     * Gets the index of patient
     * @param patient the patient object to get the index of
     * @return the index of patient
     */
    public static int getIndexOfPatient(Patient patient) {
        return patients.indexOf(patient);
    }

    public static int getIndexOfCareProvider(CareProvider careProvider) {
        return careProviders.indexOf(careProvider);
    }

    public static void storeAccountLocally(Account account, Context context) {
        if (account instanceof Patient) {
            storePatientLocally((Patient) account, context);
        }
        else if (account instanceof CareProvider) {
            storeCareProviderLocally((CareProvider) account, context);
        }
        else {
            Log.d("STOREDATA", "Not an instance of pat or cp");
        }
    }

    private static void storePatientLocally(Patient patient, Context context) {
        loadFromFile(context);
        if (patients.contains(patient)) {
            Log.d("STOREDATA", "Patient " + patient.getUsernameText() +
                    "already saved locally.");
        }
        else {
            // Give it an index and save locally.
            patients.add(patient);
            patient.setIndex(patients.indexOf(patient));
        }
    }

    private static void storeCareProviderLocally(CareProvider careProvider, Context context) {
        loadCareProvidersFromFile(context);
        if (careProviders.contains(careProvider)) {
            Log.d("STOREDATA", "Careprovider " + careProvider.getUsernameText() +
                    "already saved locally.");
        }
        else {
            // Give it an index and save locally.
            careProviders.add(careProvider);
            careProvider.setIndex(patients.indexOf(careProvider));
        }
    }
}