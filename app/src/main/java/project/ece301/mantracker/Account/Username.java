package project.ece301.mantracker.Account;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Validates and sets usernames
public class Username {
    private static final int minLength = 8;
    private static final String pattern = "[\\w.-]{"+minLength+",}";

    @NonNull
    private String username;

    public static boolean isValid(String userID){
        return Pattern.compile(pattern).matcher(userID).matches();
    }

    private static boolean isUnique(String s) {
        return true;
    }

    public Username(@NonNull String userID) throws InvalidUsernameException {
        setUserID(userID);
    }

    public String getUserID() {
        return username;

    }

    public void setUserID(String userID) throws InvalidUsernameException {
        if (!isValid(userID))
            throw new InvalidUsernameException();
        if (!isUnique(userID))
            throw new TakenUsernameException();
        else
            this.username = userID;
    }

    public class InvalidUsernameException extends Exception {
    }
    public class TakenUsernameException extends InvalidUsernameException {
    }

    @Override
    public String toString(){
        return this.username;
    }
}


