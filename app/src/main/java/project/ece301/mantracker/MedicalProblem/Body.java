package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;

public class Body {
    protected String bodySide;
    protected ArrayList<BodyLocation> bodyLocationList = new ArrayList<BodyLocation>();

    public Body(String bodySide){
        this.bodySide=bodySide;
    }

}
