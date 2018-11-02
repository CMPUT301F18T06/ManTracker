package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;

public class Body {
    protected String bodySide;
    protected ArrayList<BodyLocation> bodyLocations = new ArrayList<BodyLocation>();

    public Body(String bodySide){
        this.bodySide=bodySide;
    }

    public Body(){
    };

    public void addBodyLocation(BodyLocation bodyLocation){
        bodyLocations.add( bodyLocation );
    }

    public BodyLocation getBodyLocation(int index){
        return bodyLocations.get(index);
    }

    public void deleteBodyLocation(BodyLocation bodyLocation){
        bodyLocations.remove(bodyLocation);
    }

    public String getBodySide() {
        return bodySide;
    }

    public void setBodySide(String bodySide) {
        this.bodySide = bodySide;
    }

}
