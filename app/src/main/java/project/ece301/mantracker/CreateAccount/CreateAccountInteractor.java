package project.ece301.mantracker.CreateAccount;

public class CreateAccountInteractor {

    interface OnLoginFinishedListener {
        void onUsernameInvalidError();
        void onUsernameTakenError();

        void onEmailError();

        void onPhoneError();

        void onSuccess();
    }

    public void createAccount(final String username, final String email, final String phone,
                      final OnLoginFinishedListener listener) {
        // TODO: handle account creation
    }
}
