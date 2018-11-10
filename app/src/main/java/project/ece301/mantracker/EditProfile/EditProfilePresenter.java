package project.ece301.mantracker.EditProfile;

import android.support.annotation.NonNull;

import project.ece301.mantracker.DataManagment.DataManager;

import static android.support.v4.util.Preconditions.checkNotNull;

public class EditProfilePresenter implements EditProfileContract.Presenter {

    private final EditProfileContract.View mProfileView;
    private final DataManager mDataManager;

    public EditProfilePresenter(@NonNull DataManager dm,
                                @NonNull EditProfileContract.View profileView) {
        mProfileView = profileView; // TODO: checkNotNull
        mDataManager = dm;
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
