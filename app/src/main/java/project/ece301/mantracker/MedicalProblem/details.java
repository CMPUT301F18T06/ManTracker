/**
 * Interface Name: details
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.MedicalProblem;

import java.util.Date;

/**
 * Interface representing date/title/description details
 *
 * @version 1.0
 * @since 1.0
 */
public interface details {


    /**
     * Get date
     * @return the the date associated with this object
     */
    public String getDate();

    /**
     * Set the date
     * @param date the date associated with this object
     */
    public void setDate(String date);

    /**
     * Get the title
     * @return the title associated with this object
     */
    public String getTitle();

    /**
     * Set the title
     * @param newTitle the title associated with this object
     */
    public void setTitle(String newTitle);

    /**
     * Get the description
     * @return the description associated with this object
     */
    public String getDescription();

    /**
     * Set the description
     * @param newDescription the description to be associated with this object
     */
    public void setDescription(String newDescription);

}