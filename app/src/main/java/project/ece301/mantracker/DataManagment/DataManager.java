package project.ece301.mantracker.DataManagment;

import java.util.ArrayList;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.User.Patient;

public class DataManager {
    private static DataManager instance;

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    public DataManager() {
    }

    public Account getUser(String username) {
        return null;
    }

    public boolean addUser(Account account) {
        return true;
    }

    public ArrayList<Patient> getPatients() {
        return null;
    }

    public boolean addPatient(Patient patient) {
        return true;
    }

    public boolean deletePatient(Patient patient) {
        return true;
    }

    public ArrayList<MedicalProblem> getProblems(Patient patient) {
        return null;
    }

    public boolean addProblem(MedicalProblem problem) {
        return true;
    }

    public boolean deleteProblem(MedicalProblem problem) {
        return true;
    }

    public ArrayList<Record> getRecords(MedicalProblem problem) {
        return null;
    }

    public boolean addRecord(Record record) {
        return true;
    }

    public boolean deleteRecord(Record record) {
        return true;
    }
}
