package project.ece301.mantracker.EditProfile;

import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;

public interface EditProfileContract {
    interface View {
        void showUsername(String username);
        void showEmail(String email);
        void showPhone(String phone);
    }
    interface Presenter {
        void loadUser(String username);
        void loadUsername();
        void loadEmail();
        void loadPhone();
    }
}
