package project.ece301.mantracker.User;

import java.util.ArrayList;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;

//
public class CareProvider extends Account{
    private ArrayList<Patient> patients;

    public CareProvider() {
        super();
    }

    public CareProvider(Email email, Username username, String phone) {
        super(email, username, phone);
    }

    public CareProvider(ArrayList<Patient> patients) {}

    public Patient getPatient(Patient patient) {
        return null;
    }

    public void addPatient(Patient patient) {

    }

    public void deletePatient(Patient patient) {}

    public ArrayList<Patient> getPatientsList() { return null; }
}
