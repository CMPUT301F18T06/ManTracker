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
        else
            editor.putString(GSON_KEY+"_P", gson.toJson((Patient) account));
        editor.apply();
        Log.d(NAME, "SAVED");
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
            editor.clear().apply();
            return gson.fromJson(jsonText, Patient.class);
        } else {
            editor.clear().apply();
            return gson.fromJson(jsonText, CareProvider.class);
        }
    }
}