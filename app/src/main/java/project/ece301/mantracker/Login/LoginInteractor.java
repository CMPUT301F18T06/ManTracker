package project.ece301.mantracker.Login;

import project.ece301.mantracker.Account.Account;

import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class LoginInteractor {

    interface OnLoginFinishedListener {

        void onUsernameInvalidError();

        void onPatientLogin(Patient patient);
        void onCareProviderLogin(CareProvider careProvider);
    }

    public void login(final String username, final OnLoginFinishedListener listener) {
        DataManager dataManager = DataManager.getInstance();
        Account account = dataManager.getUser(username);
        DataManager.setLoggedInUser(account);

        if (account == null)
            listener.onUsernameInvalidError();
        else if (account.getClass() == Patient.class)
            listener.onPatientLogin((Patient) account);
        else if (account.getClass() == CareProvider.class)
            listener.onCareProviderLogin((CareProvider) account);
        else
            listener.onUsernameInvalidError();
    }
}