package project.ece301.mantracker;

import org.junit.Test;

import project.ece301.mantracker.MedicalProblem.BodyLocation;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class BodyLocationTest {

    @Test
    public void TestgetBodyPart() {
        BodyLocation bodyLocation = new BodyLocation("0,1,2","Hands");
        assertEquals("Hands", bodyLocation.getBodyPart());
    }

    @Test
    public void TestsetBodyPart() {
        BodyLocation bodyLocation = new BodyLocation("0,1,2","Hands");
        bodyLocation.setBodyPart("Hands");
        assertEquals("Hands", bodyLocation.getBodyPart());
    }

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