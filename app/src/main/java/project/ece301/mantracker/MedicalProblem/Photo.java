/**
 * Interface Name: Photo
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.MedicalProblem;

/**
 * Interface representing a photo
 */
public interface Photo {
    /**
     * Set the photo
     * @param photo the photo
     */
    void setPhoto (String photo);

    /**
     * show the photo
     * @param photo the photo
     */
    void showPhoto (String photo);

    /**
     * Get the photo
     * @return the photo
     */
    String getPhoto();
}
