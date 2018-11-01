package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;

public class Body {
    protected String bodySide;
    protected ArrayList<BodyLocation> bodyLocationList = new ArrayList<BodyLocation>();

    public Body(String bodySide){
        this.bodySide=bodySide;
    }

    public String getBodySide() {
        return bodySide;
    }

    public void setBodySide(String bodySide) {
        this.bodySide = bodySide;
    }
    
}
