package project.ece301.mantracker.data;

import android.graphics.Bitmap;

import java.util.ArrayList;

import project.ece301.mantracker.util.Photo;

public class BodyLocation implements Photo {

    private Bitmap bodyImage;
    private int xCoordinate;
    private int yCoordinate;
    protected String bodyPart;


    public BodyLocation() {
    }

    public BodyLocation(Bitmap bodyImage, String bodyPart){
        this.bodyImage=bodyImage;
        this.bodyPart=bodyPart;
    }

    public String getBodyPart(){
        return bodyPart;
    }

    public void setBodyPart(String bodyPart){
        this.bodyPart=bodyPart;
    }

    @Override
    public void setPhoto(Bitmap photo) {
        this.bodyImage=photo;
    }

    @Override
    public void showPhoto(Bitmap photo) {

    }

    @Override
    public Bitmap getPhoto() {
        return bodyImage;
    }

}
