package project.ece301.mantracker;

import org.junit.Test;

import project.ece301.mantracker.MedicalProblem.BodyLocation;

import static org.junit.Assert.*;

public class BodyLocationTest {

    @Test
    public void getBodyPart() {
        String expected = "Penis";
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setBodyPart("Penis");
        assertEquals(expected, bodyLocation.getBodyPart());
    }

    @Test
    public void setBodyPart() {
        String expected = "Balls";
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setBodyPart("Balls");
        assertEquals(expected, bodyLocation.getBodyPart());
    }

    @Test
    public void setPhoto() {
        String expected = "Nudes.jpeg";
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setPhoto("Nudes.jpeg");
        assertEquals(expected, bodyLocation.getPhoto());
    }

    @Test
    public void getPhoto() {
        String expected = "Boobs.jpeg";
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setPhoto("Boobs.jpeg");
        assertEquals(expected, bodyLocation.getPhoto());
    }

    @Test
    public void showPhoto() {

    }
}