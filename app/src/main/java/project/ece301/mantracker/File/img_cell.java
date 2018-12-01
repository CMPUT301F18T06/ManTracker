/**
 * Class Name: img_cell
 *
 * Version: Version 1.0
 *
 * Date: November 30, 2018
 *
 * Class representing the cell of an image.
 *
 * Copyright (c) Team 06, CMPUT301, University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University of Alberta
 */

package project.ece301.mantracker.File;


//TODO nned to replace with teh real photo class

public class img_cell {
    private String title;
    private Integer img;


    /**
     * Gets the title of the image in this cell
     * @return the title of the image in this cell
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the image in this cell
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the image in this cell
     * @return the image in this cell
     */
    public Integer getImg() {
        return img;
    }

    /**
     * Sets the image in this cell
     * @param img the image to set
     */
    public void setImg(Integer img) {
        this.img = img;
    }
}
