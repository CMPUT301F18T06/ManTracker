package project.ece301.mantracker.Account;

import java.util.UUID;

public class Account {
    private Username username;
    private Email email;
    private String phone;
    private String ID;
    private int localIndex;

    public Account() {
        ID = UUID.randomUUID().toString();
    }

    public Account(Email email, Username username, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        ID = UUID.randomUUID().toString(); //random ID
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(Username username) {
        this.username=username;
    }

    public Email getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public Username getUsername() {
        return username;
    }

    public String getUsernameText() {
        return username.getUserID();
    }

    public void setIndex(int index) { localIndex = index;}

    public int getIndex() {return localIndex;}

    public String getID() {return ID;}
}

