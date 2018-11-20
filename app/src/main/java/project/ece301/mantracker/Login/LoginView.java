package project.ece301.mantracker.Login;

public interface LoginView {

    void showUsernameInvalidError();

    void navigateToCareProviderHome();

    void navigateToPatientHome();

    void navigateToCreateAccount();
}