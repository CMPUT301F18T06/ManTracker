/**
 * Class Name: Email
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Represents an email
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.Account;

import android.support.annotation.NonNull;

import java.util.regex.Pattern;

//Validates and sets usernames
public class Email {
    private static final String pattern = "[\\w]+([.]+[\\w]+)*@[a-z0-9]+\\.+[a-z0-9]+";

    @NonNull
    private String email = "";

    /**
     * Checks whether a string is a valid email
     * @param email a string representing an email
     * @return True if the email string is valid. False otherwise.
     */
    public static boolean isValid(String email){
        return Pattern.compile(pattern).matcher(email).matches();
    }

    /**
     * Constructs an email object.
     * @param email an email address as a string
     * @throws InvalidEmailException if the inputted email string is invalid.
     */
    public Email(@NonNull String email) throws InvalidEmailException {
        setEmail(email);
    }

    /**
     * Returns the email as a string
     * @return email address
     */
    public String getEmail() {
        return email;

    }

    /**
     * Sets the email address
     * @param email an email address representing a string
     * @throws InvalidEmailException if the email address is invalid
     */
    public void setEmail(String email) throws InvalidEmailException {
        if (isValid(email))
            this.email = email;
        else
            throw new InvalidEmailException();
    }

    /**
     * An exception to be thrown if an email is invalid.
     */
    public class InvalidEmailException extends Exception {
    }
}
