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

import project.ece301.mantracker.User.Patient;

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
    public static ArrayList<Patient> patients = new ArrayList<Patient>();

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

    /**
     * Gets the index of patient
     * @param patient the patient object to get the index of
     * @return the index of patient
     */
    public static int getIndexOf(Patient patient) {
        return patients.indexOf(patient);
    }
}