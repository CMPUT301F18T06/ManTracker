package project.ece301.mantracker;

import java.util.ArrayList;
import java.util.Date;

public class MedicalProblem implements details {
    private Date date;
    private String description;
    private String title;
    private ArrayList<Record> associatedRecords;

    public MedicalProblem(Date initDate, String initDescription, String initTitle) {
        date = initDate;
        description = initDescription;
        title = initTitle;
        associatedRecords = new ArrayList<Record>();
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
   public void setDate(Date newDate) {

    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String newTitle) {

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

    }

    public Record getRecord(int index) {
        return new Record();
    }

    public void deleteRecord(Record record) {

    }

    public boolean hasRecord(Record record) {
        return associatedRecords.contains(record);
    }

}
