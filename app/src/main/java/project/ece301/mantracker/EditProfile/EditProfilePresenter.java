package project.ece301.mantracker.EditProfile;

import android.support.annotation.NonNull;

import static android.support.v4.util.Preconditions.checkNotNull;

public class EditProfilePresenter implements EditProfileContract.Presenter {

    private final EditProfileContract.View mProfileView;

    public EditProfilePresenter(@NonNull EditProfileContract.View profileView) {
        mProfileView = profileView; // TODO: checkNotNull
    }

    @Override
    public void loadUser() {

    }

    @Override
    public void getUsername() {

    }

    @Override
    public void getEmail() {

    }

    @Override
    public void getPhone() {

    }
}
