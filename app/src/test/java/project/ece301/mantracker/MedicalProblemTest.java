package project.ece301.mantracker;

import android.media.RemoteController;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import project.ece301.mantracker.MedicalProblem;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class MedicalProblemTest {
    @Test
    public void testsetDescription() {
        String testDescription = "Add this new description";
        MedicalProblem problem = new MedicalProblem("",
                "noTitle", "");
        problem.setDescription("Add this new description");
        assertEquals(testDescription, problem.getDescription());
    }
    @Test
    public void testgetDescription() {
        String testDescription = "This string should match";
        MedicalProblem problem = new MedicalProblem("This string should match",
                "noTitle", "");
        String returnedDescription = problem.getDescription();
        assertEquals(testDescription, returnedDescription);
    }
    @Test
    public void testsetDate() {
        String testDate = "2018-06-15";
        MedicalProblem problem = new MedicalProblem("", "",
                "");
        problem.setDate("2018-06-15");
        String returnedDate = problem.getDate();
        assertEquals(testDate, returnedDate);

    }
    @Test
    public void testgetDate() {
        String testDate = "2018-06-15";
        MedicalProblem problem = new MedicalProblem("", "",
                "2018-06-15");
        String returnedDate = problem.getDate();
        assertEquals(testDate, returnedDate);
    }
    @Test
    public void testsetTitle() {
        String testTitle = "Add this new title";
        MedicalProblem problem = new MedicalProblem("no description",
                "", "");
        problem.setTitle("Add this new title");
        assertEquals(testTitle, problem.getTitle());
    }
    @Test
    public void testgetTitle() {
        String testTitle = "Get this title";
        MedicalProblem problem = new MedicalProblem("no desciption",
                "Get this title", "");
        String returnedTitle = problem.getTitle();
        assertEquals(testTitle, returnedTitle);
    }
    @Test
    public void testaddRecord() {
        MedicalProblem problem = new MedicalProblem("",
                "", "");
        Record record = new Record();
        problem.addRecord(record);
        assertTrue(problem.hasRecord(record));
    }
    @Test
    public void testgetRecord() {
        MedicalProblem problem = new MedicalProblem("",
                "", "");
        Record record = new Record();
        problem.addRecord(record);
        assertTrue(problem.hasRecord(record));

        Record returnedRecord = problem.getRecord(0);
        assertEquals(record, returnedRecord);

    }
    @Test
    public void testdeleteRecord() {
        MedicalProblem problem = new MedicalProblem("",
                "", "");
        Record record = new Record();
        problem.addRecord(record);
        assertTrue(problem.hasRecord(record));

        problem.deleteRecord(record);
        assertFalse(problem.hasRecord(record));
    }
    @Test
    public void testhasRecord() {
        MedicalProblem problem = new MedicalProblem("",
                "", "");
        Record record = new Record();
        problem.addRecord(record);
        assertTrue(problem.hasRecord(record));
    }
}
