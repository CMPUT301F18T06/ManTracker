/**
 * Class Name: Comment
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.MedicalProblem;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import project.ece301.mantracker.Account.Account;

/**
 * Represents a Comment
 * Comments have a date, a user and content
 *
 * @Version 1.0
 * @Since 1.0
 */
public class Comment implements Comparable<Comment> {
    private static final SimpleDateFormat dF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
            Locale.getDefault());
    private Date date;
    private Account user;
    private String comment;

    /**
     * Constructs a Comment object
     *
     * @param date a date to be associated with the comment
     * @param user a user who posted the comment
     * @param comment the content of the comment
     */
    public Comment(Date date, Account user, String comment) {
        this.date = date;
        this.user = user;
        this.comment = comment;
    }

    /**
     * Constructs a Comment Object
     *
     * @param user a user who posted the comment
     * @param comment the content of the comment
     */
    public Comment(Account user, String comment) {
        this.date = new Date();
        this.user = user;
        this.comment = comment;
    }

    /**
     * Get the Date associated with the comment
     * @return the date associated with the comment
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set a date to be associated with the comment
     * @param date the date to be associated with the comment
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Set a date to be associated with the comment
     * @param dateText the date to be associated with the comment
     */
    public void setDate(String dateText) {
        try {
            this.date = dF.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the user who posted the comment
     * @return the user who posted the comment
     */
    public Account getUser() {
        return user;
    }

    /**
     * Get the UserID of the user who posted the comment
     * @return the UserID of the user who posted the comment
     */
    public String getUserID() {
        return user.getUsernameText();
    }

    /**
     * Set the user who posted the comment
     * @param user the user who posted the comment
     */
    public void setUser(Account user) {
        this.user = user;
    }

    /**
     * Get the content of the comment
     * @return the content of the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the content of the comment
     * @param comment the content of the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Get the date of the comment as a string
     * @return the date of the comment as a string
     */
    public String getDateAsString(){
        return dF.format(date);
    }

    @Override
    public String toString(){
        return getDateAsString() + " | " + getUserID() + " | " + getComment();
    }

    @Override
    public int compareTo(@NonNull Comment comment) {
        return getDate().compareTo(comment.getDate());
    }
}
