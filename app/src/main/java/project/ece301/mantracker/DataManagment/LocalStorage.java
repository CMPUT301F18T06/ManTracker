package project.ece301.mantracker.DataManagment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class LocalStorage {
    private static final String NAME = "loginSession";
    private static final String GSON_KEY = "loginSessionGson";

    public static void saveLoginSession(Context context, Account account){
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        if (account instanceof CareProvider)
            editor.putString(GSON_KEY+"_CP", gson.toJson((CareProvider)account));
        else if (account != null)
            editor.putString(GSON_KEY+"_P", gson.toJson((Patient) account));
        else {
            editor.putString(GSON_KEY+"_CP", "");
            editor.putString(GSON_KEY+"_P", "");
        }
        editor.commit();
    }

    public static Account loadLoginSession(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonText = sharedPreferences.getString(GSON_KEY+"_CP", "");
        if(jsonText.isEmpty()) {
            jsonText = sharedPreferences.getString(GSON_KEY+"_P", "");
            if(jsonText.isEmpty()) {
                return null;
            }
            editor.putString(GSON_KEY+"_CP", "");
            editor.putString(GSON_KEY+"_P", "");
            editor.apply();
            Log.d(NAME, "Loading patient");
            Patient patient = gson.fromJson(jsonText, Patient.class);
            editor.commit();
            Log.d(NAME, patient.getUsernameText());
            if (patient != null)
                return patient;
        } else {
            Log.d(NAME, "Loading care provider");
            editor.putString(GSON_KEY+"_CP", "");
            editor.putString(GSON_KEY+"_P", "");
            editor.apply();
            CareProvider careProvider = gson.fromJson(jsonText, CareProvider.class);
        }
        return null;
    }
}