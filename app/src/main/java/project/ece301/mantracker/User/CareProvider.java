package project.ece301.mantracker.User;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Dictionary;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;

import static project.ece301.mantracker.File.StoreData.patients;

//
public class CareProvider extends Account {
    private ArrayList<Patient> patients;

    public CareProvider() {
        super();
        patients = new ArrayList<Patient>();
    }

    public CareProvider(Email email, Username username, String phone) {
        super(email, username, phone, "Not Applicable");
        patients = new ArrayList<Patient>();
    }

    public CareProvider(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public Patient getPatient(int index) {
        return patients.get(index);
    }

    public MedicalProblem getPatientProblem(Patient patient, String problemID) {
        for (MedicalProblem problem : patient.getAllProblems()) {
            if (problem.getId().equals(problemID))
                return problem;
        }
        return null;
    }

    public ArrayList<MedicalProblem> getPatientProblems(int index) {
        return patients.get(index).getAllProblems();
    }

    public MedicalProblem getPatientProblem(int patientIndex, int problemIndex) {
        return patients.get(patientIndex).getProblem(problemIndex);
    }

    public Record getPatientRecords(int patientIndex, int problemIndex, int recordIndex) {
        return patients.get(patientIndex).getRecord(problemIndex, recordIndex);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void deletePatient(Patient patient) {
        patients.remove(patient);
    }

    public ArrayList<Patient> getPatientsList() {
        return patients;
    }

    public int getPatientProblemCount(int index) {
        return (null != patients ? (null != patients.get(index).getAllProblems()
                ? patients.get(index).getAllProblems().size() : 0) : 0);
    }

    public int getPatientCount() {
        return (getPatients().size());
    }

    private ArrayList<Patient> getPatients() {
        return patients;
    }
}
