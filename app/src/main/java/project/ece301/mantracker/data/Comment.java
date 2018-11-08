package project.ece301.mantracker.data;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Comment implements Comparable<Comment> {
    private static final SimpleDateFormat dF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
            Locale.getDefault());
    private Date date;
    private Account user;
    private String comment;

    public Comment(Date date, Account user, String comment) {
        this.date = date;
        this.user = user;
        this.comment = comment;
    }

    public Comment(Account user, String comment) {
        this.date = new Date();
        this.user = user;
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String dateText) {
        try {
            this.date = dF.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Account getUser() {
        return user;
    }

    public String getUserID() {
        return "not you";
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


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
