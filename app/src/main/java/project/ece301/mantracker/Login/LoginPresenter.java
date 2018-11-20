package project.ece301.mantracker.Login;

import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener{
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    public void validateCredentials(String username) {
        loginInteractor.login(username, this);
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