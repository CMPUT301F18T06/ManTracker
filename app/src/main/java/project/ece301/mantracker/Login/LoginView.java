package project.ece301.mantracker.Login;

public interface LoginView {

    void showUsernameNotUniqueError();

    void showUsernameInvalidError();

    void navigateToHome();
}