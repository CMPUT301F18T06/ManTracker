package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;

public class Body {
    protected String bodySide;
    protected ArrayList<BodyLocation> bodyLocations = new ArrayList<BodyLocation>();

    public Body(String bodySide){
        this.bodySide=bodySide;
    }

    public void addBodyLocation(String bodyimage, String bodyPart){
        bodyLocations.add( new BodyLocation(bodyimage,bodyPart) );
    }

    public void removeBodyLocation(int index){
        bodyLocations.remove(index);
    }

    public String getBodySide() {
        return bodySide;
    }

    public void setBodySide(String bodySide) {
        this.bodySide = bodySide;
    }

}
