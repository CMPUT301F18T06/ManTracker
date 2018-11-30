package project.ece301.mantracker.CreateAccount;


import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.File.StoreData;
import project.ece301.mantracker.MedicalProblem.ElasticSearchPatientController;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;


public class CreateAccountInteractor {

    interface OnCreateAccountFinishedListener {
        void onUsernameInvalidError();
        void onUsernameTakenError();

        void onEmailError();

        void onPhoneError();

        void onPatientCreated(Patient patient);
        void onCareProviderCreated(CareProvider careProvider);
    }

    public void createAccount(final String username, final String email, final String phone,
                      final boolean isCareProvider, final OnCreateAccountFinishedListener listener) {
        DataManager dataManager = DataManager.getInstance();

        try {
            Account account;
            Username username1 = new Username(username);
            Email email1 = new Email(email);
            //TODO: validate phone number

            if (isCareProvider) {
                CareProvider careProvider = new CareProvider(email1, username1, phone);
                listener.onCareProviderCreated(careProvider);
                account = careProvider;
            } else {
                Patient patient = new Patient(email1, username1, phone);
                StoreData.patients.add(patient); // TODO: Get rid of this when elastic search works.
                listener.onPatientCreated(patient);
                account = patient;

            }

            dataManager.addUser(account); //TODO: seperate add Patient and Careproviders?
            dataManager.setLoggedInUser(account);


        } catch (Username.TakenUsernameException e) {
            listener.onUsernameTakenError();
        } catch (Username.InvalidUsernameException e) {
            listener.onUsernameInvalidError();
        } catch (Email.InvalidEmailException e) {
            listener.onEmailError();
        }
    }
}
