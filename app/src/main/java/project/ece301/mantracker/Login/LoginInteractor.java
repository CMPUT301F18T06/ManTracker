package project.ece301.mantracker.Login;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class LoginInteractor {

    interface OnLoginFinishedListener {

        void onUsernameInvalidError();

        void onPatientLogin();
        void onCareProviderLogin();
    }

    public void login(final String username, final OnLoginFinishedListener listener) {
        DataManager dataManager = DataManager.getInstance();
        Account account = dataManager.getUser(username);

        if (account == null)
            listener.onUsernameInvalidError();
        else if (account.getClass() == Patient.class)
            listener.onPatientLogin();
        else if (account.getClass() == CareProvider.class)
            listener.onCareProviderLogin();
        else
            listener.onUsernameInvalidError();
    }
}