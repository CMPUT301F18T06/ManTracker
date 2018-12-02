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

import android.support.annotation.NonNull;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.DataManagment.DataManager;

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
        this.loadUsername();
        this.loadEmail();
        this.loadPhone();
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
}
