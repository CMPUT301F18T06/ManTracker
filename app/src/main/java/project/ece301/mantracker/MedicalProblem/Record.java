/**
 * Class Name: Record
 *
 * Version: Version 1.0
 *
 * Date: November 31, 2018
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.MedicalProblem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import project.ece301.mantracker.details;

/**
 * Represents a Record
 * Records have a date, ID, description, Title, problem ID, Patient, photos
 * and BodyLocations
 */
public class Record implements details, Serializable { //implementing serializable allows us to pass an instance of record across activities
    private String date;

    private String ID;
    private String Description;
    private String Title;
    private String associatedProblemID;
    private String associatedPatient;
    private String locationName; //used when querying for location key words

    private ArrayList<BodyLocation> bodyLocations;

    private ArrayList<String> commentlist;

    private Object geolocation;

    private ArrayList<String> photos;

    /**
     * Constructs a Record object
     */
    public Record(){

         bodyLocations = new ArrayList<BodyLocation>();
         photos = new ArrayList<String>();
         ID = UUID.randomUUID().toString();
    }

    public void setLocationName(String locationName) {this.locationName = locationName;}

    public String getLocationName() {return this.locationName;}

    /**
     * Sets the associated patient of this record
     * @param username the username of the patient to associate with this record
     */
    public void setAssociatedPatient(String username) {
        associatedPatient = username;
    }

    /**
     * Gets the patient associated with this record
     * @return the patient assosciated with this record
     */
    public String getAssociatedPatient() {
        return associatedPatient;
    }

    /**
     * Gets the ID of the record
     * @return the ID of this record
     */
    public String getID() {
        return ID;
    }

    /**
     * Get the ID of the problem associated with this record
     * @return the ID of the problem associated with this record
     */
    public String getProblemID() {
        return this.associatedProblemID;
    }

    /**
     * Set the ID of the problem associated with this record
     * @param problemID the ID of the problem associated with this record
     */
    public void setProblemID(String problemID) {
        this.associatedProblemID = problemID;
    }

    /**
     * Get the Date of this Record
     * @return the date of this record
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of this Record
     * @param newDate the date to give to this record
     */
    public void setDate(String newDate) {
        date = newDate;
    }

    /**
     * Get the title of this record
     * @return the title of this record
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Set the title of this record
     * @param title the title to give to this record
     */
    public void setTitle(String title) {
        Title = title;

    }

    /**
     * Get the description of this record
     * @return the description of this record
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Set the description of this record
     * @param description the description to give to this record
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Gets a list of BodyLocations associated with this record
     * @return
     */
    public ArrayList<BodyLocation> getBodyLocationList(){
        return bodyLocations;
    }


    /**
     * Adds a BodyLocation to this record
     * @param bdlocation the bodylocation to add to this record
     */
    // Input a bodylocation class
    public void addBodyLocation(BodyLocation bdlocation){
        bodyLocations.add(bdlocation);
    }

    public void deleteBodyLocation(int index){
        bodyLocations.remove(index);
    }

    /**
     * Get the BodyLocation associated with this record
     * @return a BodyLocation
     */
    public Object getBodyLocation(){
        return null;
    }

    /**
     * Adds a photo to this record
     * @param photo
     */
    public void addPhoto(String photo){
        photos.add(photo);
    }


    /**
     * Gets the number of photos attached to this record
     * @return the number of photos associated with this record
     */
    public int getNumOfPhotos(){
        return 0;
    }
    public ArrayList<String> getPhotoList(){
        return photos;

    }

    /**
     * Sets a GeoLocation to be associated with this record
     * @param geolocation the GeoLocation to associate with this record
     */
    //    Input a geolocation class
    public void setGeoLocation(Object geolocation){

    }

    /**
     * Gets the geolocation associated with this record
     * @return a GeoLocation
     */
    public Object getGeoLocation(){
        return null;
    }

    @Override
    public String toString() {
        return "Patient: " + associatedPatient + " | " + this.Title + " | " + this.Description + " | " + this.date;
    }
    //    Input a commnet class

    /**
     * Adds a comment to this record
     * @param comment the comment to add
     */
    public void addComment(String comment){

    }
    //    Input a commnet class

    /**
     * Deletes a comment
     * @param comment the comment to delete
     */
    public void deleteComment(String comment){

    }

    /**
     * Get the number of comments associated with this record
     * @return the number of comments associated with this record
     */
    public int getNumOfComment(){
        return 0;
    }

    /**
     * Get a list of comments associated with this record
     * @return the list of comments associated with this record
     */
    public ArrayList<String> getCommentlist() {
        return null;

    }
}
