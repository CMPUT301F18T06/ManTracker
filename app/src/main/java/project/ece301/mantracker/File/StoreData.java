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

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;
// TODO: covert to Saving Accounts -- see rfurrer Feelsbook

public class StoreData {

    public static final String FILENAME = "ManTracker.sav";
    public static final String CP_FILENAME = "ManTrackerCareProvidors.sav";
    public static ArrayList<Patient> patients = new ArrayList<Patient>();
    public static ArrayList<CareProvider> careProviders = new ArrayList<CareProvider>();

    /* loads the objects from the file to the emotion list */

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

    public static int getIndexOfPatient(Patient patient) {
        return patients.indexOf(patient);
    }

    public static int getIndexOfCareProvider(CareProvider careProvider) {
        return careProviders.indexOf(careProvider);
    }
}