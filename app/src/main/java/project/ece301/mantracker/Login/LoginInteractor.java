package project.ece301.mantracker.Login;


import android.util.Log;
import android.content.Context;

import project.ece301.mantracker.Account.Account;

import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.File.StoreData;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class LoginInteractor {

    interface OnLoginFinishedListener {

        void onUsernameInvalidError();

        void onPatientLogin(Patient patient);
        void onCareProviderLogin(CareProvider careProvider);
    }


    public void login(final String username, final OnLoginFinishedListener listener, Context context) {
        DataManager dataManager = DataManager.getInstance(context);
        Account account = dataManager.getUser(username);
        dataManager.setLoggedInUser(account);

        StoreData.storeAccountLocally(account, context);

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