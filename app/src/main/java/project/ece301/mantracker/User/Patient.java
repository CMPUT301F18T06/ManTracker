package project.ece301.mantracker.User;

import java.util.ArrayList;
import java.util.UUID;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Email;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.MedicalProblem.BodyLocation;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.MedicalProblem.Geolocation;

//
public class Patient extends Account{
    private ArrayList<MedicalProblem> problemList = new ArrayList<MedicalProblem>();
    private ArrayList<BodyLocation> bodyLocations = new ArrayList<BodyLocation>();
    private ArrayList<Geolocation> geoLocations = new ArrayList<Geolocation>();
    private ArrayList<Record> records = new ArrayList<Record>();
    private String patientID;

    public Patient() {
        patientID = UUID.randomUUID().toString(); //random ID
    }

    public Patient(Email email, Username username, String phone) {
        super(email, username, phone);
        patientID = UUID.randomUUID().toString(); //random ID
    }

    public String getID() {return patientID;}

    public MedicalProblem getProblem(MedicalProblem problem) { return null; }

    public void addProblem(MedicalProblem problem) {
        problemList.add(problem);
    }

    public void deleteProblem(MedicalProblem problem) {}

    public ArrayList<MedicalProblem> getAllProblems() {
        return problemList;
    }

    public BodyLocation getBodyLocation(BodyLocation bodyLocation) { return null; }

    public void addBodyLocation(BodyLocation bodyLocation) {}

    public void deleteBodyLocation(BodyLocation bodyLocation) {}

    public ArrayList<Record> getAllRecords() { return null; }

    public void addRecord(Record record) {}

    public Record getRecord(Integer rID) { return null; }

    public void removeRecord(Integer rID) {}
}
