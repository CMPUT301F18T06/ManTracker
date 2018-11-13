package project.ece301.mantracker.EditProfile;

import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;

public interface EditProfileContract {
    interface View {
        void updateUsername(Username username);
        void updateEmail(Email email);
        void updatePhone(String phone);
    }
    interface Presenter {
        void loadUser();
        void getUsername();
        void getEmail();
        void getPhone();
    }
}
