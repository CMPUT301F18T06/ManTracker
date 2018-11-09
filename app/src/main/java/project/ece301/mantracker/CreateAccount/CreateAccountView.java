package project.ece301.mantracker.CreateAccount;

public interface CreateAccountView {

    void showUsernameNotUniqueError();
    void showUsernameInvalidError();

    void showEmailError();

    void showPhoneError();

    void navigateToHome();
}
