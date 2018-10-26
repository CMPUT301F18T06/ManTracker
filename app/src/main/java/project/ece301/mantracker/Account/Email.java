package project.ece301.mantracker.Account;

import android.support.annotation.NonNull;

import java.util.regex.Pattern;

//Validates and sets usernames
public class Email {
    private static final String pattern = "[\\w]+([.]+[\\w]+)*@[a-z0-9]+\\.+[a-z0-9]+";

    @NonNull
    private String email = "";

    public static boolean isValid(String email){
        return Pattern.compile(pattern).matcher(email).find();
    }

    public Email(@NonNull String email) throws InvalidEmailException {
        setEmail(email);
    }

    public String getEmail() {
        return email;

    }

    public void setEmail(String email) throws InvalidEmailException {
        if (isValid(email))
            this.email = email;
        else
            throw new InvalidEmailException();
    }

    public class InvalidEmailException extends Exception {
    }
}
