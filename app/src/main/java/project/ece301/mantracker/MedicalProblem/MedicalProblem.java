/**
 * Class Name: Medical Problem
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;
import java.util.UUID;

import project.ece301.mantracker.User.Patient;

/**
 * Represents a Medical Problem
 * Medical Problems have details, an ArrayList of Records, associated patient ID and username,
 * and an id.
 *
 * @version 1.0
 * @see details
 * @see Record
 * @since 1.0
 */
public class MedicalProblem implements details {
    private String date;
    private String description;
    private String title;
    private ArrayList<Record> associatedRecords;
    private ArrayList<Comment> comments;
    private String id;  //unique id for problems. Will be used for ES
    private String associatedPatientID;
    private String patientUsername;

    /**
     * Constructs a MedicalProblem object
     */
    public MedicalProblem() {
    }

    /**
     * Constructs a Medical Problem object
     *
     * @param initDescription the description of the medical problem
     * @param initTitle the title of the medical problem
     * @param initDate the date of the medical problem
     * @param patientID the ID of the patient whose medical problem this is
     * @param username the username of the patient whose medical problem this is
     */
    public MedicalProblem(String initDescription, String initTitle, String initDate, String patientID,
                          String username) {
        associatedRecords = new ArrayList<Record>();
        comments = new ArrayList<>();
        description = initDescription;
        title = initTitle;
        date = initDate;
        id = UUID.randomUUID().toString();
        associatedPatientID = patientID;
        patientUsername = username;
    }
    public String getPatientUsername () {return patientUsername;}

    /**
     * Gets the ID of the patient associated with this medical problem
     * @return the patient ID
     */
    public String getPatientID() {return associatedPatientID;}

    /**
     * Get the ID of this MedicalProblem
     * @return the ID of this MedicalProblem
     */
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

    /**
     * Add a Record to this MedicalProblem
     * @param newRecord a Record
     */
    public void addRecord(Record newRecord) {
        associatedRecords.add(newRecord);
    }

    public void setRecord(int index, Record record) {
        associatedRecords.set(index,record);
    }

    public ArrayList<Record> getAllRecords() {
        return associatedRecords;
    }

    /**
     * Get a Record associated with this MedicalProblem
     * @param index the index of the record
     * @return a Record
     */
    public Record getRecord(int index) {
        return associatedRecords.get(index);
    }

    /**
     * Delete a Record associated with this MedicalProblem
     * @param record the Record to delete
     */
    public void deleteRecord(Record record) {
        associatedRecords.remove(record);
    }

    /**
     * Check if this MedicalProblem contains a Record
     * @param record the record to look for
     * @return True if the MedicalProblem has record. False otherwise.
     */
    public boolean hasRecord(Record record) {
        return associatedRecords.contains(record);
    }

    @Override
    public String toString(){
        return "Patient: " + this.patientUsername + " | "+ this.title + " | " +
                this.description + " | " + this.date;
    }

    /**
     * Get the number of Records associated with this MedicalProblem
     * @return number of Records associated with this MedicalProblem
     */
    public int getRecordCount() { return associatedRecords.size();}

  
    public void removeRecord(int rID) {
        associatedRecords.remove(rID);
    }
    public void removeRecord(Record record) {
        associatedRecords.remove(record);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
