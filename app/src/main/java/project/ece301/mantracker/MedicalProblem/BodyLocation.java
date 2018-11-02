package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;

public class BodyLocation implements Photo {

    protected String bodyImage;
    protected ArrayList<Integer> ImageCordinates = new ArrayList<Integer>();
    protected String bodyPart;


    public BodyLocation(String bodyImage, String bodyPart){
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
