/**
 * Class Name: Username
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Represents a username.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

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

    /**
     * Checks whether userID is a valid username.
     * @param userID
     * @return True if userID is valid. False otherwise.
     */
    public static boolean isValid(String userID){
        return Pattern.compile(pattern).matcher(userID).matches();
    }

    private static boolean isUnique(String s) {
        return true;
    }

    /**
     * Constructs a Username Object
     *
     * @param userID username
     * @throws InvalidUsernameException if userID is an invalid username.
     */
    public Username(@NonNull String userID) throws InvalidUsernameException {
        setUserID(userID);
    }

    /**
     * Gets the username as a string.
     * @return the username as a string.
     */
    public String getUserID() {
        return username;

    }

    /**
     * Sets the username
     * @param userID the username as string
     * @throws InvalidUsernameException if userID is an invalid username.
     */
    public void setUserID(String userID) throws InvalidUsernameException {
        if (!isValid(userID))
            throw new InvalidUsernameException();
        if (!isUnique(userID))
            throw new TakenUsernameException();
        else
            this.username = userID;
    }

    /**
     * Exception Name: InvalidUsernameException
     *
     * Thrown when a username is invalid.
     */
    public class InvalidUsernameException extends Exception {
    }

    /**
     * Exception Name: TakenUsernameException
     *
     * Thrown when a username has already been taken.
     */
    public class TakenUsernameException extends InvalidUsernameException {
    }

    /**
     * Returns the username as a string
     * @return username as a string
     */
    @Override
    public String toString(){
        return this.username;
    }
}


