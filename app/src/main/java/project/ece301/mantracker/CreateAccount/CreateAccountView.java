package project.ece301.mantracker.CreateAccount;

public interface CreateAccountView {

    void showUsernameNotUniqueError();

    void showUsernameError();

    void showPasswordError();

    void showEmailError();

    void showPhoneError();

    void navigateToHome();
}