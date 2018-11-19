package project.ece301.mantracker.EditProfile;

import android.support.annotation.NonNull;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.DataManagment.DataManager;

import static android.support.v4.util.Preconditions.checkNotNull;

public class EditProfilePresenter implements EditProfileContract.Presenter {

    private final EditProfileContract.View mProfileView;
    private final DataManager mDataManager;
    private Account user;

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
