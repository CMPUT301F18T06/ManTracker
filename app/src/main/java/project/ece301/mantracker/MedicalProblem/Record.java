package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import project.ece301.mantracker.details;

public class Record implements details {
    private Date date;
    private Integer ID;
    private String Description;
    private String Title;
    public Date testing_date;
    public Integer testing_ID;
    private String comment;
    private ArrayList<String> commentlist;

    private Object geolocation;
    private Object bdlocation;
    private ArrayList<Object> bodylocationpointlist;
    private ArrayList<Object> photolist;

    public Record(String Description , String title ,   Object geolocation,Object bdlocation,
                  ArrayList<Object> bodylocationpointlist, ArrayList<Object> photolist, ArrayList<String> commentlist){

        Random rand = new Random();
        int  n = rand.nextInt(1000000);
        this.date = new Date();
        this.ID = n;
        this.testing_date = this.date;
        this.testing_ID = this.ID;
        this.Description = Description;
        this.Title = title;
        this.geolocation = geolocation;
        this.bdlocation = bdlocation;
        this.bodylocationpointlist = bodylocationpointlist;
        this.photolist = photolist;
        this.commentlist = commentlist;

    }

    public Record(){

    }

    public Record(String Description , String title ){
        Random rand = new Random();
        int  n = rand.nextInt(1000000);
        this.date = new Date();
        this.ID = n;
        this.testing_date = this.date;
        this.testing_ID = this.ID;
        this.Description = Description;
        this.Title = title;

    }

    public Integer getID() {
        return null;
    }

    public void setID() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date newDate) {
        date = newDate;
    }

    public String getTitle() {
        return null;
    }

    public void setTitle(String title) {



    }

    public String getDescription() {
        return null;
    }

    public void setDescription(String description) {

    }


//  addBodyLocationPoint(BodyLocationPoint): void
//  deleteBodyLocationPoint(BodyLocationPoint):void
//  getNumOfBodyLocationPoints()

    //    Input a BodyLocationpoint class
    public void addBodyLocationPoint(Object bdlocationpoint){

    }
    //    Input a BodyLocationPoint class
    public void deleteBodyLocationPoint(Object bdlocationpoint){

    }
    public int getNumOfBodyLocationPoints(){
        return 0;
    }
    public Object getBodyLocationPointsList(){
        return null;
    }
//
// getBodyLocation():BodyLocation;
// setBodyLocation(Bodylocation):

    //    Input a bodylocation class
    public void setBodyLocation(Object bdlocation){

    }

    public Object getBodyLocation(){
        return null;
    }

    //addPhoto(Photo):void
//deletePhoto(Photo):void
//getNumOfPhotots():Integer
//    Input a photo class
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

//    + getGeoLocation():Geolocation
//+ setGeoLocation(Geolocation):void

    //    Input a geolocation class
    public void setGeoLocation(Object geolocation){

    }
    public Object getGeoLocation(){
        return null;
    }


//    addComment()
//    deleteComment()
//    getNumOfComment()

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
