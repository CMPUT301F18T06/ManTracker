package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;


public class MedicalProblem implements details {
    private String date;
    private String description;
    private String title;
    private ArrayList<Record> associatedRecords;

    public MedicalProblem() {
    }

    public MedicalProblem(String initDescription, String initTitle, String initDate) {
        associatedRecords = new ArrayList<Record>();
        description = initDescription;
        title = initTitle;
        date = initDate;
    }

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
        return this.title + " | " + this.description + " | " + this.date;
    }

}
