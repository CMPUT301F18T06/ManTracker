package project.ece301.mantracker.User;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;

//
public class CareProvider extends Account implements Parcelable {
    private ArrayList<Patient> patients;

    public CareProvider() {
        super();
    }

    public CareProvider(Email email, Username username, String phone) {
        super(email, username, phone);
    }

    public CareProvider(ArrayList<Patient> patients) {}

    protected CareProvider(Parcel in) {
    }

    public static final Creator<CareProvider> CREATOR = new Creator<CareProvider>() {
        @Override
        public CareProvider createFromParcel(Parcel in) {
            return new CareProvider(in);
        }

        @Override
        public CareProvider[] newArray(int size) {
            return new CareProvider[size];
        }
    };

    public Patient getPatient(Patient patient) {
        return null;
    }

    public void addPatient(Patient patient) {

    }

    public void deletePatient(Patient patient) {}

    public ArrayList<Patient> getPatientsList() { return null; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
