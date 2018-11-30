package project.ece301.mantracker.MedicalProblem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import project.ece301.mantracker.details;

public class Record implements details, Serializable { //implementing serializable allows us to pass an instance of record across activities
    private String date;

    private String ID;
    private String Description;
    private String Title;
    private String associatedProblemID;
    private String associatedPatient;

    private ArrayList<BodyLocation> bodyLocations;

    private ArrayList<String> commentlist;

    private Object geolocation;

    private ArrayList<String> photos;

    public Record(){

         bodyLocations = new ArrayList<BodyLocation>();
         photos = new ArrayList<String>();
         ID = UUID.randomUUID().toString();
    }

    public void setAssociatedPatient(String username) {
        associatedPatient = username;
    }

    public String getAssociatedPatient() {
        return associatedPatient;
    }

    public String getID() {
        return ID;
    }

//
    public String getProblemID() {
        return this.associatedProblemID;
    }

    public void setProblemID(String problemID) {
        this.associatedProblemID = problemID;
    }

    public String getDate() {
        return date;
    }
//
    public void setDate(String newDate) {
        date = newDate;
    }

    public String getTitle() {
        return Title;
    }
//
    public void setTitle(String title) {
        Title = title;

    }

    public String getDescription() {
        return Description;
    }
//
    public void setDescription(String description) {
        Description = description;
    }
//

    public ArrayList<BodyLocation> getBodyLocationList(){
        return bodyLocations;
    }


    // Input a bodylocation class
    public void addBodyLocation(BodyLocation bdlocation){
        bodyLocations.add(bdlocation);
    }

    public void deleteBodyLocation(int index){
        bodyLocations.remove(index);
    }


    public Object getBodyLocation(){
        return null;
    }


    public void addPhoto(String photo){
        photos.add(photo);
    }


    public int getNumOfPhotos(){
        return 0;
    }
    public ArrayList<Object> getPhotoList(){
        return null;

    }

    //    Input a geolocation class
    public void setGeoLocation(Object geolocation){

    }
    public Object getGeoLocation(){
        return null;
    }

    @Override
    public String toString() {
        return "Patient: " + associatedPatient + " | " + this.Title + " | " + this.Description + " | " + this.date;
    }
    //    Input a commnet class
    public void addComment(String comment){

    }
    //    Input a commnet class
    public void deleteComment(String comment){

    }
    public int getNumOfComment(){
        return 0;
    }

    public ArrayList<String> getCommentlist() {
        return null;

    }
}
