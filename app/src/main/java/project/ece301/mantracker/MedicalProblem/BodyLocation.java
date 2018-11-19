package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;

public class BodyLocation implements Photo {

    protected String bodyImage;
    protected String ImageCordinates;


    public BodyLocation(String bodyImage, String Coordinates){
        this.bodyImage=bodyImage;
        this.ImageCordinates=Coordinates;
    }


    @Override
    public void setPhoto(String image) {
        this.bodyImage=image;
    }

    @Override
    public String getPhoto() {
        return bodyImage;
    }

    @Override
    public void showPhoto(String image) {
    }

}
