package project.ece301.mantracker.CreateAccount;

import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public interface CreateAccountView {

    void showUsernameNotUniqueError();
    void showUsernameInvalidError();

    void showEmailError();

    void showPhoneError();

    void navigateToPatientHome(Patient patient);
    void navigateToCareProviderHome(CareProvider careProvider);
}
