package project.ece301.mantracker;

import project.ece301.mantracker.Activity.MainActivity;
import project.ece301.mantracker.MedicalProblem.Record;
import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

public class RecordTest extends ActivityInstrumentationTestCase2 {

    Object geolocation = new Object();
    Object bdlocation = new Object();
    Object photo = new Object();
    Object bodylocationpoint = new Object();
    ArrayList<Object> bodylocationpointlist = new ArrayList<Object>();
    ArrayList<Object> photolist = new ArrayList<Object>();
    ArrayList<String> commentlist = new ArrayList<String>();

//    Geolocation geolocaiton = new Geolocation();
//    Bodylocation bdlocation = new Bodylocation();
//    ArrayList<BodyLocationPoint> bodylocationpointlist = ArrayList<BodyLocationPoint>();
//    ArrayList<Photo> photolist = new ArrayList<Photo>();
//    public Record record = new Record("Description 1" , "title_1", geolocaiton,bdlocation, bodylocationpointlist,photolist);
//
//
//
//    class Record {
//        private Date date;
//        private Integer ID;
//        private String Description;
//        private String Title;
//        public Date testing_date;
//        public Integer testing_ID;
//
//        private Geolocation geolocation;
//        private Bodylocation bdlocation;
//        private ArrayList<BodyLocationPoint> bodylocationlist;
//        private ArrayList<Photo> photolist;
//
//        public Record(String Description , String title , Geolocation geolocation, Bodylocation bdlocation,
//                      ArrayList<BodyLocationPoint> bodylocationpointlist, ArrayList<Photo> photolist){
//
//            Random rand = new Random();
//            int  n = rand.nextInt(1000000);
//            this.date = new Date();
//            this.ID = n;
//            this.testing_date = this.date;
//            this.testing_ID = this.ID;
//            this.Description = Description;
//            this.Title = title;
//            this.geolocation = geolocation;
//            this.bdlocation = bdlocation;
//            this.bodylocationlist = bodylocationlist;
//            this.photolist = photolist;
//
//        }
//    }

    public RecordTest(){
        super(MainActivity.class);
    }

    public void getBodylocationpointList(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        assertEquals(bodylocationpointlist,record.getBodyLocationPointsList());

    }


    //    BodyLocationPoint()
    public void testAddBodyLocationPoint(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);

        Object new_bdlocation = new Object();
        record.addBodyLocationPoint(new_bdlocation);
        ArrayList<Object> a = (ArrayList<Object>) record.getBodyLocationPointsList();
        assertEquals(1,record.getNumOfBodyLocationPoints());
        assertEquals( a.get(0), new_bdlocation);




    }

    public void testDeleteBodyLocationPoint(){
        Record record  = new Record("Description 1" , "title 1", geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        Object new_bdlocation_point = new Object();
        Object another_new_bdlocation_point = new Object();
        record.addBodyLocationPoint(new_bdlocation_point);
        record.addBodyLocationPoint(another_new_bdlocation_point);
        ArrayList<Object> a = (ArrayList<Object>) record.getBodyLocationPointsList();

        assertEquals(2,record.getNumOfBodyLocationPoints());
        assertEquals( a.get(0), new_bdlocation_point);
        assertEquals( a.get(1), another_new_bdlocation_point);
        record.deleteBodyLocationPoint(another_new_bdlocation_point);
        assertEquals(1,record.getNumOfBodyLocationPoints());
        assertEquals( a.get(0), new_bdlocation_point);

    }

    public void testgetNumOfBodyLocationPoints(){
        Record record  = new Record("Description 1" , "title 1", geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        Object new_bdlocationpoint = new Object();
        Object another_new_bdlocationpoint = new Object();
        record.addBodyLocationPoint(new_bdlocationpoint);
        assertEquals(1,record.getNumOfBodyLocationPoints());
        record.addBodyLocationPoint(another_new_bdlocationpoint);
        assertEquals(2,record.getNumOfBodyLocationPoints());
        record.deleteBodyLocationPoint(another_new_bdlocationpoint);
        assertEquals(1,record.getNumOfBodyLocationPoints());

    }


    public void testgetID(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        assertEquals(record.testing_ID , record.getID());
    }

//    BodyLocation

    public void testsetBodyLocation(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        Object new_bdlocation = new Object();
        record.setBodyLocation( new_bdlocation);
        assertEquals(record.getBodyLocation(),new_bdlocation);

    }

    public void testgetBodyLocation(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        assertEquals(record.getBodyLocation(),bdlocation);


    }





    public void testgetDate(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        assertEquals(record.testing_date , record.getDate());
    }

//    Description

    public void testsetDescription(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        record.setDescription("Description_set_description");
        assertEquals("Description_set_description",record.getDescription());


    }

    public void testgetSecription(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        assertEquals("Description 1",record.getDescription());

    }

//    Title

    public void testsetTitle(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        record.setTitle("title_set_title");
        assertEquals("title_set_title",record.getTitle());

    }

    public void testgetTitle(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        assertEquals("title_1",record.getTitle());

    }

//    Photos_urls_list

    public void testgetphotolist(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        assertEquals(record.getPhotoList(),photolist);

    }

    public void testaddPhoto(){
        Object photo = new Object();
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        record.addPhoto(photo);
        ArrayList<Object> photoList = record.getPhotoList();
        assertEquals(1,record.getNumOfPhotos());
        assertEquals(photo,photolist.get(0) );
    }
    public void testdeletePhoto(){
        Object photo = new Object();
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        record.addPhoto(photo);
        assertEquals(1,record.getNumOfPhotos());
        record.deletePhoto(photo);
        assertEquals(0,record.getNumOfPhotos());


    }

    public void testgetNumOfPhotots(){
        Object photo = new Object();
        Object another_photo = new Object();
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        record.addPhoto(photo);
        assertEquals(1,record.getNumOfPhotos());
        record.addPhoto(another_photo);
        assertEquals(2,record.getNumOfPhotos());
        record.deletePhoto(photo);
        assertEquals(1,record.getNumOfPhotos());
        record.deletePhoto(another_photo);
        assertEquals(0,record.getNumOfPhotos());


    }
//    Geolocation

    public void testsetgeolocation(){
        Object new_geolocation =  new Object();
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        record.setGeoLocation(new_geolocation);
        assertEquals(record.getGeoLocation() , new_geolocation);

    }

    public void testgetgeolocation(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        assertEquals(record.getGeoLocation() , geolocation);

    }

    public void testgetCommentlist(){
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        assertEquals(record.getCommentlist() , commentlist);




    }


    public void testaddComment(){
        String new_comment = new String("apple");
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        record.addComment(new_comment);
        assertEquals("apple",record.getCommentlist().get(0));
        assertEquals(1,record.getNumOfComment());



    }

    public void testdeleteComment(){
        String new_comment = new String("apple");
        String another_new_comment = new String("banana");
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        record.addComment(new_comment);
        assertEquals(1,record.getNumOfComment());
        record.addComment(another_new_comment);
        assertEquals(2,record.getNumOfComment());
        record.deleteComment(new_comment);
        assertEquals(1,record.getNumOfComment());
        record.deleteComment(another_new_comment);
        assertEquals(0,record.getNumOfComment());

    }

    public void getNumOfCommen(){
        String new_comment = new String("apple");
        String another_new_comment = new String("banana");
        Record record = new Record("Description 1" , "title_1",geolocation,bdlocation,bodylocationpointlist,photolist,commentlist);
        record.addComment(new_comment);
        assertEquals(1,record.getNumOfComment());
        record.addComment(another_new_comment);
        assertEquals(2,record.getNumOfComment());
        record.deleteComment(new_comment);
        assertEquals(1,record.getNumOfComment());
        record.deleteComment(another_new_comment);
        assertEquals(0,record.getNumOfComment());
    }
}
