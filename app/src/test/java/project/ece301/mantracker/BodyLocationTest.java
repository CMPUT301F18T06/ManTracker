package project.ece301.mantracker;

import org.junit.Test;

import project.ece301.mantracker.MedicalProblem.BodyLocation;

import static junit.framework.Assert.assertEquals;

public class BodyLocationTest {

    @Test
    public void TestsetPhoto() {
        BodyLocation bodyLocation = new BodyLocation("6,72,3,8","Legs");
        bodyLocation.setPhoto("Legs.jpeg");
        assertEquals("Legs.jpeg", bodyLocation.getPhoto());
    }

    @Test
    public void TestgetPhoto() {
        BodyLocation bodyLocation = new BodyLocation("32,3,4,3","ForeHead");
        bodyLocation.setPhoto("Arms.jpeg");
        assertEquals("Arms.jpeg", bodyLocation.getPhoto());
    }

    @Test
    public void TestshowPhoto() {

    }
}