package project.ece301.mantracker.Login;

public interface LoginView {

    void showUsernameNotUniqueError();

    void showUsernameError();

    void showPasswordError();

    void showEmailError();

    void showPhoneError();

    void navigateToHome();
}