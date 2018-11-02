package project.ece301.mantracker;

import org.junit.Test;

import project.ece301.mantracker.MedicalProblem.Body;
import project.ece301.mantracker.MedicalProblem.BodyLocation;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class BodyTest {

    @Test
    public void TestaddBodyLocation() {

        Body mybody = new Body("back");
        BodyLocation bodyL  = new BodyLocation("2,1,2","face");

        mybody.addBodyLocation(bodyL);

        assertEquals(bodyL,mybody.getBodyLocation(0));
    }

    @Test
    public void TestdeleteBodyLocation() {

        Body mybody = new Body("front");
        BodyLocation bodyLocation1  = new BodyLocation("2,1,2","face");
        BodyLocation bodyLocation2 = new BodyLocation("123,23","foot");

        mybody.addBodyLocation(bodyLocation1);
        mybody.addBodyLocation(bodyLocation2);

        mybody.deleteBodyLocation(bodyLocation1);

        assertEquals(bodyLocation2,mybody.getBodyLocation(0));
    }
}
