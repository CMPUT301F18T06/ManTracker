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

public class StoreData {

    public static final String FILENAME = "ManTracker.sav";
    public static ArrayList<Patient> patients = new ArrayList<Patient>();

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
}