package project.ece301.mantracker.Login;

public class LoginInteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();
        void onUsernameTakenError();

        void onPasswordError();

        void onEmailError();

        void onPhoneError();

        void onSuccess();
    }

    public void login(final String username, final String password, final String email, final String phone,
                      final OnLoginFinishedListener listener) {
        // handle login
    }
}