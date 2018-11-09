package project.ece301.mantracker.Login;

public class LoginInteractor {

    interface OnLoginFinishedListener {

        void onUsernameInvalidError();
        void onUsernameTakenError();

        void onSuccess();
    }

    public void login(final String username, final OnLoginFinishedListener listener) {
        // TODO: handle login
    }
}