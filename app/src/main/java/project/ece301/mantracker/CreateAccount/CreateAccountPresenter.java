package project.ece301.mantracker.CreateAccount;

import android.content.Context;

import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.Presenter;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

public class CreateAccountPresenter extends Presenter implements CreateAccountInteractor.OnCreateAccountFinishedListener{
    private CreateAccountView createAccountView;
    private CreateAccountInteractor createAccountInteractor;
    private DataManager dataManager;

    CreateAccountPresenter(Context context, CreateAccountView createAccountView,
                           CreateAccountInteractor createAccountInteractor) {
        super(context);
        dataManager = DataManager.getInstance(context);
        this.createAccountView = createAccountView;
        this.createAccountInteractor = createAccountInteractor;
    }

    public void validateCredentials(String username, String email, String phone, boolean isCareProvider,
                                    Context context) {
        createAccountInteractor.createAccount(username, email, phone, isCareProvider,this,
                context);
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
    public void onPatientCreated(Patient patient) {
        if (createAccountView != null) {
            createAccountView.navigateToPatientHome(patient);
        }
    }

    @Override
    public void onCareProviderCreated(CareProvider careProvider) {
        if (createAccountView != null) {
            createAccountView.navigateToCareProviderHome(careProvider);
        }
    }
}
