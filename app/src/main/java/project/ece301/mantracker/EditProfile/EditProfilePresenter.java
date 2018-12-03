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

import android.content.Context;
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
    private Context mContext;

    /**
     * Constructs an EditProfilePresenter
     * @param dm a DataManager object
     * @param editProfileView an EditProfileView
     * @param userIndex the index of the user whose profile should be loaded.
     */
    public EditProfilePresenter(@NonNull DataManager dm,
                                @NonNull EditProfileContract.View editProfileView,
                                int userIndex, Context context) {
        mProfileView = editProfileView; // TODO: checkNotNull
        mDataManager = dm;
        mContext = context;

        this.loadUser(userIndex);
    }

    @Override
    public void loadUser(int index) {
        this.user = this.mDataManager.getLoggedInUser();
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
        if (!user.getUsernameText().equals(username)) {
            try {
                user.setUsername(new Username(username, mContext));
            } catch (Username.InvalidUsernameException e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            user.setEmail(new Email(email));
        } catch (Email.InvalidEmailException e) {
            e.printStackTrace();
            return false;
        }
        user.setPhone(phone);
        try {
            mDataManager.setLoggedInUser(user);
            Log.d("EditProfile", "Set logged in user successfully!");
            if (user instanceof Patient) {
                Log.d("EditProfile", "USER INSTANCE OF PATIENT");
            }
            else if (user instanceof CareProvider) {
                Log.d("EditProfile", "USER INSTANCE OF CAREPROVIDER");
            }
            try {
                CareProvider newCarepro = new CareProvider(new Email("testemail@test.com"),
                        new Username("testUsername987", mContext), "780123456");
                Boolean addSuccess = mDataManager.addUser(newCarepro);
                Log.d("EditProfile", "Added Successfully? "+ String.valueOf(addSuccess));
//                Log.d("EditProfile", "New name: " + user.getUsernameText());
                Account justAdded = mDataManager.getUser(newCarepro.getUsernameText());
                Log.d("EditProfile", "Found newCarePro? " + String.valueOf(justAdded != null));
                Log.d("EditProfile", "Just added: " + justAdded.getUsernameText());
            } catch (Email.InvalidEmailException | Username.InvalidUsernameException e) {
                Log.d("EditProfile", "invalid new guy");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        Log.d("EditProfile", "Saved successfully!");
        return true;
    }

    public int getUserIndex() {
        return user.getIndex();
    }
}
