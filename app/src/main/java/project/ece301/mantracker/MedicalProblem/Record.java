package project.ece301.mantracker.MedicalProblem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import project.ece301.mantracker.details;

public class Record implements details { //implementing serializable allows us to pass an instance of record across activities
    private String date;
    //private Integer ID;
    private String Description;
    private String Title;
    private String associatedProblemID;


    private ArrayList<BodyLocation> bodyLocations;

    private ArrayList<String> commentlist;

    private Object geolocation;

    private ArrayList<Object> photolist;

    public Record(){
         bodyLocations = new ArrayList<BodyLocation>();
    }

    public Integer getID() {
        return null;
    }

    public void setID() {

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
//
//    //    Input a BodyLocationpoint class

    //    Input a BodyLocationPoint class
    public void deleteBodyLocationPoint(Object bdlocationpoint){

    }
    public int getNumOfBodyLocationPoints(){
        return 0;
    }
    public Object getBodyLocationPointsList(){
        return null;
    }


    // Input a bodylocation class
    public void addBodyLocation(BodyLocation bdlocation){
        bodyLocations.add(bdlocation);
    }



    public Object getBodyLocation(){
        return null;
    }

//    //addPhoto(Photo):void
////deletePhoto(Photo):void
////getNumOfPhotots():Integer
////    Input a photo class
    public void addPhoto(Object photo){

    }
    //    Input a photo class
    public void deletePhoto(Object photo){

    }
    public int getNumOfPhotos(){
        return 0;
    }
    public ArrayList<Object> getPhotoList(){
        return null;

    }
//
////    + getGeoLocation():Geolocation
////+ setGeoLocation(Geolocation):void
//
    //    Input a geolocation class
    public void setGeoLocation(Object geolocation){

    }
    public Object getGeoLocation(){
        return null;
    }
//
//
////    addComment()
////    deleteComment()
////    getNumOfComment()
//


    @Override
    public String toString() {
        return this.Title + " | " + this.Description + " | " + this.date;
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
