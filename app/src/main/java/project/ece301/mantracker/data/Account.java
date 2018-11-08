package project.ece301.mantracker.data;

import project.ece301.mantracker.util.Email;
import project.ece301.mantracker.util.Username;

public class Account {
    //includes username, email and phone #
    private Username username;
    private Email email;
    private String phone;

    public Account() {}

    public Account(Email email, Username username, String phone) {
    }

    public void setEmail(Email email) {

    }

    public void setPhone(String phone) {

    }

    public void setUsername(Username username) {}

    public Email getEmail() {
        return null;
    }

    public String getPhone() {
        return null;
    }

    public Username getUsername() {
        return null;
    }
}

