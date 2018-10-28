package project.ece301.mantracker;

import org.junit.Test;

import project.ece301.mantracker.MedicalProblem.BodyLocation;

import static org.junit.Assert.*;

public class BodyLocationTest {

    @Test
    public void getBodyPart() {
        String expected = "Head";
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setBodyPart("Penis");
        assertEquals(expected, bodyLocation.getBodyPart());
    }

    @Test
    public void setBodyPart() {
        String expected = "Hands";
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setBodyPart("Balls");
        assertEquals(expected, bodyLocation.getBodyPart());
    }

    @Test
    public void setPhoto() {
        String expected = "Legs.jpeg";
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setPhoto("Legs.jpeg");
        assertEquals(expected, bodyLocation.getPhoto());
    }

    @Test
    public void getPhoto() {
        String expected = "Arms.jpeg";
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setPhoto("Arms.jpeg");
        assertEquals(expected, bodyLocation.getPhoto());
    }

    @Test
    public void showPhoto() {

    }
}