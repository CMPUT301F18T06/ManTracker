/**
 * Class Name: EditProfilePresenter
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Presenter for edit profile. This class talks to the data model and tells the view to update
 * itself.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.EditProfile;

import android.os.Debug;
import android.support.annotation.NonNull;
import android.util.Log;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.File.StoreData;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Presenter for the EditProfileActivity
 * The presenter tells its view to update itself and gets data from its datamodel.
 *
 * @version 1.0
 * @since 1.0
 */
public class EditProfilePresenter implements EditProfileContract.Presenter {

    private final EditProfileContract.View mProfileView;
    private final DataManager mDataManager;
    private Account user;

    /**
     * Constructs an EditProfilePresenter
     * @param dm a DataManager object
     * @param editProfileView an EditProfileView
     * @param username the username of the user whose profile should be loaded.
     */
    public EditProfilePresenter(@NonNull DataManager dm,
                                @NonNull EditProfileContract.View editProfileView,
                                String username) {
        mProfileView = editProfileView; // TODO: checkNotNull
        mDataManager = dm;
        this.loadUser(username);
    }

    @Override
    public void loadUser(String username) {
        this.user = this.mDataManager.getUser(username);
        if (this.user != null) {
            this.loadUsername();
            this.loadEmail();
            this.loadPhone();
        }
    }

    @Override
    public void loadUsername() {
        this.mProfileView.showUsername(this.user.getUsername());
    }

    @Override
    public void loadEmail() {
        this.mProfileView.showEmail(this.user.getEmail());
    }

    @Override
    public void loadPhone() {
        this.mProfileView.showPhone(this.user.getPhone());
    }

    @Override
    public boolean saveUser(String username, String email, String phone) {
        int index = StoreData.getIndexOf(user);
        Log.d("EditProfile", "Saving User: " + Integer.toString(index));
        if (user instanceof CareProvider) {
            try {
                CareProvider newCP = new CareProvider(new Email(email),
                        new Username(username), phone);
                newCP.setIndex(index);

                // update local storage
                StoreData.careProviders.set(index, newCP);

                // update elastic search by adding the new account and deleting the old
                mDataManager.addUser(newCP);
                mDataManager.deleteUser(user);

                // update the logged in user.
                user = newCP;
                DataManager.setLoggedInUser(user);
            } catch (Username.InvalidUsernameException | Email.InvalidEmailException e) {
                e.printStackTrace();
                return false;
            }
        }
        else if (user instanceof Patient) {
            try {
                Patient newPatient = new Patient(new Email(email), new Username(username),
                        phone);
                newPatient.setIndex(index);

                // update local storage
                StoreData.patients.set(index, newPatient);
                Log.d("EditProfile", "Saved User: " +
                        StoreData.patients.get(index).getUsername().toString());

                // update elastic search by adding the new account and deleting the old
                mDataManager.addUser(newPatient);
                mDataManager.deleteUser(user);

                // update the logged in user.
                user = newPatient;
                DataManager.setLoggedInUser(user);
            } catch (Username.InvalidUsernameException | Email.InvalidEmailException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public int getUserIndex() {
        return user.getIndex();
    }
}
