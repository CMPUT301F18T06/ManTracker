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
        return Pattern.compile(pattern).matcher(userID).matches() && isUnique(userID);
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
        if (isValid(userID))
            this.username = userID;
        else
            throw new InvalidUsernameException();
    }

    public class InvalidUsernameException extends Exception {
    }
}


