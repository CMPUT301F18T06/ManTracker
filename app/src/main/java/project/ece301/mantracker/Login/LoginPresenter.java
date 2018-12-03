package project.ece301.mantracker.Login;

import android.content.Context;

import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.Presenter;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class LoginPresenter extends Presenter implements LoginInteractor.OnLoginFinishedListener{
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    LoginPresenter(Context context, LoginView loginView, LoginInteractor loginInteractor) {
        super(context);
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    public void tryLoadingSession() {
        DataManager dataManager = DataManager.getInstance(context);
        dataManager.tryLoadingLoginSession();
        if (dataManager.getLoggedInUser() != null
                && dataManager.getLoggedInUser() instanceof CareProvider)
            loginView.navigateToCareProviderHome((CareProvider) dataManager.getLoggedInUser());
        if (dataManager.getLoggedInUser() != null
                && dataManager.getLoggedInUser() instanceof Patient)
            loginView.navigateToPatientHome((Patient) dataManager.getLoggedInUser());

    }

    public void validateCredentials(String username) {
        loginInteractor.login(username, this, context);
    }

    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameInvalidError() {
        if (loginView != null) {
            loginView.showUsernameInvalidError();
        }
    }

    @Override
    public void onPatientLogin(Patient patient) {
        if (loginView != null) {
            loginView.navigateToPatientHome(patient);
        }
    }

    @Override
    public void onCareProviderLogin(CareProvider careProvider) {
        if (loginView != null) {
            loginView.navigateToCareProviderHome(careProvider);
        }
    }
}