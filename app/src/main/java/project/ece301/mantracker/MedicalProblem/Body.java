/**
 * Class Name: Body
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Class representing a body which can have body locations.
 * Bodies can have a body side, e.g. front or back.
 * Bodies have a list of BodyLocations.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;

/**
 * Class representing a body which can have body locations.
 * Bodies can have a body side, e.g. front or back.
 * Bodies have a list of BodyLocations.
 *
 * @version 1.0
 * @see BodyLocation
 * @since 1.0
 */
public class Body {
    protected String bodySide;
    protected ArrayList<BodyLocation> bodyLocations = new ArrayList<BodyLocation>();

    /**
     * Constructs a Body Object
     *
     * @param bodySide a string representing the side of the body (front, back, etc)
     */
    public Body(String bodySide){
        this.bodySide=bodySide;
    }

    /**
     * Constructs a body object without specifying a body side
     */
    public Body(){
    };

    /**
     * Adds a body location the body
     *
     * @param bodyLocation the BodyLocation object to add
     */
    public void addBodyLocation(BodyLocation bodyLocation){
        bodyLocations.add( bodyLocation );
    }

    /**
     * Gets a BodyLocation attached to this Body by index
     * @param index index of the body location to get
     * @return a BodyLocation
     */
    public BodyLocation getBodyLocation(int index){
        return bodyLocations.get(index);
    }

    /**
     * Deletes a BodyLocation from this Body
     *
     * @param bodyLocation the BodyLocation to delete
     */
    public void deleteBodyLocation(BodyLocation bodyLocation){
        bodyLocations.remove(bodyLocation);
    }

    /**
     * Gets the Body Side of this body object
     * @return a body side
     */
    public String getBodySide() {
        return bodySide;
    }

    /**
     * Sets the body side of this body
     * @param bodySide the body side
     */
    public void setBodySide(String bodySide) {
        this.bodySide = bodySide;
    }

}
