package project.ece301.mantracker.CreateAccount;

public class CreateAccountInteractor {

    interface OnCreateAccountFinishedListener {
        void onUsernameInvalidError();
        void onUsernameTakenError();

        void onEmailError();

        void onPhoneError();

        void onSuccess();
    }

    public void createAccount(final String username, final String email, final String phone,
                      final OnCreateAccountFinishedListener listener) {
        // TODO: handle account creation
    }
}
