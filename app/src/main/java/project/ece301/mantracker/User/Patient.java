package project.ece301.mantracker.User;

import java.util.ArrayList;

import project.ece301.mantracker.MedicalProblem.BodyLocation;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.MedicalProblem.Geolocation;

//
public class Patient extends Account{
    private ArrayList<MedicalProblem> problemList;
    private ArrayList<BodyLocation> bodyLocations;
    private ArrayList<Geolocation> geoLocations;
    private ArrayList<Record> records;

    public Patient() {

    }

    public MedicalProblem getProblem(MedicalProblem problem) { return null; }

    public void addProblem(MedicalProblem problem) {}

    public void deleteProblem(MedicalProblem problem) {}

    public ArrayList<MedicalProblem> getAllProblems() {
        return null;
    }

    public BodyLocation getBodyLocation(BodyLocation bodyLocation) { return null; }

    public void addBodyLocation(BodyLocation bodyLocation) {}

    public void deleteBodyLocation(BodyLocation bodyLocation) {}

    public ArrayList<Record> getAllRecords() { return null; }

    public void addRecord(Record record) {}

    public Record getRecord(Integer rID) { return null; }

    public void removeRecord(Integer rID) {}
}
