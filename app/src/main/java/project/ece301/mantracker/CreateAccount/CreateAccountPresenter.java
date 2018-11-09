package project.ece301.mantracker.CreateAccount;

public class CreateAccountPresenter implements CreateAccountInteractor.OnCreateAccountFinishedListener{
    private CreateAccountView createAccountView;
    private CreateAccountInteractor createAccountInteractor;

    CreateAccountPresenter(CreateAccountView createAccountView, CreateAccountInteractor createAccountInteractor) {
        this.createAccountView = createAccountView;
        this.createAccountInteractor = createAccountInteractor;
    }

    public void validateCredentials(String username, String email, String phone) {
        createAccountInteractor.createAccount(username, email, phone, this);
    }

    public void onDestroy() {
        createAccountView = null;
    }

    @Override
    public void onUsernameInvalidError() {
        if (createAccountView != null) {
            createAccountView.showUsernameInvalidError();
        }
    }

    @Override
    public void onUsernameTakenError() {
        if (createAccountView != null) {
            createAccountView.showUsernameNotUniqueError();
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
