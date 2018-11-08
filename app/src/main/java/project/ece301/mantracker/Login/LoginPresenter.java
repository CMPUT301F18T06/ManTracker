package project.ece301.mantracker.Login;

public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener{
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    public void validateCredentials(String username, String password, String email, String phone) {
        loginInteractor.login(username, password, email, phone, this);
    }

    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.showUsernameError();
        }
    }

    @Override
    public void onUsernameTakenError() {
        if (loginView != null) {
            loginView.showUsernameNotUniqueError();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.showPasswordError();
        }
    }

    @Override
    public void onEmailError() {
        if (loginView != null) {
            loginView.showEmailError();
        }
    }

    @Override
    public void onPhoneError() {
        if (loginView != null) {
            loginView.showPhoneError();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
