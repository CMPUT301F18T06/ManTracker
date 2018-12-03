/**
 * Interface Name: EditProfileContract
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * View and Presenter interfaces for the EditProfileActivity.
 * EditProfileActivity should implement a EditProfileContract.view and it should have a
 * EditProfileContract.Presenter.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.EditProfile;

import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;

/**
 * This Contract puts the view and presenter into a single location, indicating that
 * these Views and Presenters are related. The View should have a Presenter and the
 * presenter should tell the view to update.
 *
 * @version 1.0
 * @since 1.0
 */
public interface EditProfileContract {
    interface View {
        /**
         * Show the username on the screen
         * @param username the username to display
         */
        void showUsername(Username username);

        /**
         * Show the email on the screen
         * @param email the email to display
         */
        void showEmail(Email email);

        /**
         * Show the number on the screen
         * @param phone the phone number to display
         */
        void showPhone(String phone);
    }
    interface Presenter {

        /**
         * Loads a user
         * @param username the username of the user to load
         */
        void loadUser(String username);

        /**
         * Loads a username
         */
        void loadUsername();

        /**
         * Loads an email
         */
        void loadEmail();

        /**
         * Loads a phone number
         */
        void loadPhone();

        /**
         * Saves the new user information on elastic search and locally
         * @param username
         * @param email
         * @param phone
         */
        boolean saveUser(String username, String email, String phone);
    }
}
