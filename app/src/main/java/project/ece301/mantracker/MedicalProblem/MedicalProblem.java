package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;
import java.util.UUID;

import project.ece301.mantracker.User.Patient;


public class MedicalProblem implements details {
    private String date;
    private String description;
    private String title;
    private ArrayList<Record> associatedRecords;
    private String id;  //unique id for problems. Will be used for ES
    private String associatedPatientID;
    private String patientUsername;

    public MedicalProblem() {
    }

    public MedicalProblem(String initDescription, String initTitle, String initDate, String patientID,
                          String username) {
        associatedRecords = new ArrayList<Record>();
        description = initDescription;
        title = initTitle;
        date = initDate;
        id = UUID.randomUUID().toString();
        associatedPatientID = patientID;
        patientUsername = username;
    }

    public String getPatientID() {return associatedPatientID;}

    public String getId() {return id;}

    @Override
    public String getDate() {
        return date;
    }

    @Override
   public void setDate(String newDate) {
        date = newDate;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String newDescription) {
        description = newDescription;
    }

    public void addRecord(Record newRecord) {
        associatedRecords.add(newRecord);
    }

    public Record getRecord(int index) {
        return associatedRecords.get(index);
    }

    public void deleteRecord(Record record) {
        associatedRecords.remove(record);
    }

    public boolean hasRecord(Record record) {
        return associatedRecords.contains(record);
    }

    @Override
    public String toString(){
        return "Patient: " + this.patientUsername + " | "+ this.title + " | " +
                this.description + " | " + this.date;
    }

    public int getRecordCount() { return associatedRecords.size();}

}
