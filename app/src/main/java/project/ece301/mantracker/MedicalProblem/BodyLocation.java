package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;

public class BodyLocation implements Photo {

    protected String bodyImage;
    protected String label;
    protected String ImageCordinates;


    public BodyLocation(String bodyImage, String Coordinates, String label){
        this.bodyImage=bodyImage;
        this.ImageCordinates=Coordinates;
        this.label=label;
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

    public String getBodyImage(){
        return bodyImage;
    }
    public String getLabel(){
        return label;
    }
    public String getImageCordinates(){
        return ImageCordinates;
    }

}
