/**
 * Class Name: BodyLocation
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.MedicalProblem;

import java.util.ArrayList;

/**
 * Class representing body location.
 * Body locations can have photos and image coordinates.
 *
 * @version 1.0
 * @see Photo
 * @since 1.0
 */
public class BodyLocation implements Photo {

    protected String bodyImage;
    protected String label;
    protected String ImageCordinates;

    /**
     * Constructs a BodyLocation
     * @param bodyImage a body image to associate with this body location
     * @param Coordinates the coordinates of the body image on this body location
     * @param label the label of this BodyLocation
     */
    public BodyLocation(String bodyImage, String Coordinates, String label){
        this.bodyImage=bodyImage;
        this.ImageCordinates=Coordinates;
        this.label=label;
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
    public String getLabel(){
        return label;
    }
    public String getImageCordinates(){
        return ImageCordinates;
    }

}
