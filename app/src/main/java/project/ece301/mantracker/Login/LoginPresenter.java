package project.ece301.mantracker.Login;

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
    public void onUsernameTakenError() {
        if (loginView != null) {
            loginView.showUsernameNotUniqueError();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}