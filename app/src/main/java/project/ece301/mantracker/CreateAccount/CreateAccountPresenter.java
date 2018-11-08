package project.ece301.mantracker.CreateAccount;

public class CreateAccountPresenter implements CreateAccountInteractor.OnLoginFinishedListener{
    private CreateAccountView createAccountView;
    private CreateAccountInteractor createAccountInteractor;

    CreateAccountPresenter(CreateAccountView createAccountView, CreateAccountInteractor createAccountInteractor) {
        this.createAccountView = createAccountView;
        this.createAccountInteractor = createAccountInteractor;
    }

    public void validateCredentials(String username, String password, String email, String phone) {
        createAccountInteractor.login(username, password, email, phone, this);
    }

    public void onDestroy() {
        createAccountView = null;
    }

    @Override
    public void onUsernameError() {
        if (createAccountView != null) {
            createAccountView.showUsernameError();
        }
    }

    @Override
    public void onUsernameTakenError() {
        if (createAccountView != null) {
            createAccountView.showUsernameNotUniqueError();
        }
    }

    @Override
    public void onPasswordError() {
        if (createAccountView != null) {
            createAccountView.showPasswordError();
        }
    }

    @Override
    public void onEmailError() {
        if (createAccountView != null) {
            createAccountView.showEmailError();
        }
    }

    @Override
    public void onPhoneError() {
        if (createAccountView != null) {
            createAccountView.showPhoneError();
        }
    }

    @Override
    public void onSuccess() {
        if (createAccountView != null) {
            createAccountView.navigateToHome();
        }
    }
}
