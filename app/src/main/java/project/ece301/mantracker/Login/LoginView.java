package project.ece301.mantracker.Login;

import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public interface LoginView {

    void showUsernameInvalidError();

    void navigateToCareProviderHome(CareProvider careProvider);

    void navigateToPatientHome(Patient patient);

    void navigateToCreateAccount();
}