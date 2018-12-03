package project.ece301.mantracker;

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
    //    public void setDate(Date date);

    /**
     * Get the title
     * @return the title associated with this object
     */
    public String getTitle();

    /**
     * Set the title
     * @param title the title associated with this object
     */
    public void setTitle(String title);

    /**
     * Get the description
     * @return the description associated with this object
     */
    public String getDescription();

    /**
     * Set the description
     * @param description the description to be associated with this object
     */
    public void setDescription(String description);

}
