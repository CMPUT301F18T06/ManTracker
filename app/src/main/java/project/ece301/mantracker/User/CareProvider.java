package project.ece301.mantracker.User;

import java.util.ArrayList;

import project.ece301.mantracker.Account.Account;

//
public class CareProvider extends Account{
    private ArrayList<Patient> patients;

    public CareProvider() {
        this.patients = new ArrayList<>();
    }

    public CareProvider(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public Patient getPatient(Integer ind) {
        return this.patients.get(ind);
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

    public void deletePatient(Patient patient) {
        this.patients.remove(patient);
    }

    public ArrayList<Patient> getPatientsList() { return this.patients; }
}
