package project.ece301.mantracker.CreateAccount;


import android.util.Log;
import java.util.Random;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.File.StoreData;
import project.ece301.mantracker.MedicalProblem.ElasticSearchPatientController;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;


public class CreateAccountInteractor {
    final static String TAG = "CreateAccountInteractor";

    interface OnCreateAccountFinishedListener {
        void onUsernameInvalidError();
        void onUsernameTakenError();

        void onEmailError();

        void onPhoneError();

        void onPatientCreated(Patient patient);
        void onCareProviderCreated(CareProvider careProvider);
    }

    public String generateShortCode() {
        //https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        while(builder.length() < 4) { //length of random string
            int index = (int) (rnd.nextFloat() * CHARS.length());
            builder.append(CHARS.charAt(index));
        }
        String randomString = builder.toString();
        return randomString;
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
                String patientCode = generateShortCode();
                Patient patient = new Patient(email1, username1, phone, patientCode);
                StoreData.patients.add(patient); // TODO: Get rid of this when elastic search works.
                listener.onPatientCreated(patient);
                account = patient;

            }

            Log.d("ADDINGUSER", username);
            boolean result = dataManager.addUser(account); //TODO: seperate add Patient and Careproviders?
            Log.d(TAG, "createAccount: " + result);
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
