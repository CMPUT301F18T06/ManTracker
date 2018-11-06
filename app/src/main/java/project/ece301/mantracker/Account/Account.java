package project.ece301.mantracker.Account;

public class Account {
    private Username username;
    private Email email;
    private String phone;

    public Account() {}

    public Account(Email email, Username username, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Email getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public Username getUsername() {
        return this.username;
    }
}

