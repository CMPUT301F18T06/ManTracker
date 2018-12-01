/**
 * Class Name: BodyLocation
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Class representing body location.
 * Body locations can have images and image coordinates.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;

public class BodyLocation implements Photo {

    protected String bodyImage;
    protected String ImageCordinates;


    /**
     * Constructs a BodyLocation
     * @param bodyImage a body image to associate with this body location
     * @param Coordinates the coordinates of the body image on this body location
     */
    public BodyLocation(String bodyImage, String Coordinates){
        this.bodyImage=bodyImage;
        this.ImageCordinates=Coordinates;
    }


    /**
     * Sets a body image photo to this body location
     * @param image a body image photo
     */
    @Override
    public void setPhoto(String image) {
        this.bodyImage=image;
    }

    /**
     * Gets a body image photo associated with this body location
     * @return
     */
    @Override
    public String getPhoto() {
        return bodyImage;
    }

    @Override
    public void showPhoto(String image) {
    }

    /**
     * Gets a body image photo associated with this body location
     * @return
     */
    public String getBodyImage(){
        return bodyImage;
    }

}
