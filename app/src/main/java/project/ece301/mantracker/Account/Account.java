/**
 * Class Name: Account
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Represents a user account.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.Account;

import java.util.UUID;

/**
 * Represents a user account
 * Accounts have a username, an email and a phone.
 *
 * @version 1.0
 * @see Username
 * @see Email
 * @since 1.0
 */
public class Account {
    private Username username;
    private Email email;
    private String phone;
    private String ID;
    private int localIndex;
    private String shortCode;

     /**
     * Constructs Account objects
     *
     */
    public Account() {
        ID = UUID.randomUUID().toString();
    }

    /**
     * Constructs Account Objects
     *
     * @param email email associated with the account
     * @param username username associated with the account
     * @param phone phone associated with the account
     */
    public Account(Email email, Username username, String phone, String shortCode) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.shortCode = shortCode;
        ID = UUID.randomUUID().toString(); //random ID
    }


    public void setShortCode(String shortCode) {this.shortCode = shortCode;}

    public String getShortCode(){ return this.shortCode;}

    /**
     * Sets email of the account
     * @param email
     */
    public void setEmail(Email email) {
        this.email = email;
    }

    /**
     * Sets phone number of the account
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets username of the account
     * @param username
     */
    public void setUsername(Username username) {
        this.username=username;
    }

    /**
     * Returns the email associated with this account. Null if no email.
     * @return The email associated with this account. Null if no email.
     */
    public Email getEmail() {
        return this.email;
    }

    /**
     * Returns the phone number associated with the account.
     * @return Phone number associated with this account. Null if no phone.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Gets the username associated with the account.
     * @return The username associated with the account. Null if no username.
     */
    public Username getUsername() {
        return username;
    }

    public String getUsernameText() {
        return username.getUserID();
    }

    public void setIndex(int index) { localIndex = index;}

    public int getIndex() {return localIndex;}

    public String getID() {return ID;}

//     https://www.geeksforgeeks.org/overriding-equals-method-in-java/
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Account or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Account)) {
            return false;
        }

        // typecast o to Account so that we can compare data members
        Account account = (Account) o;

        // Compare the data members and return accordingly
        return this.getUsername().toString().equals(account.getUsername().toString());
    }
}

