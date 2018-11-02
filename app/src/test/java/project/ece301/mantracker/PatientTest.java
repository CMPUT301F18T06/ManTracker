package project.ece301.mantracker;

import org.junit.Test;

import java.util.ArrayList;

import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.BodyLocation;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.User.Patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PatientTest {
    @Test
    public void testGetProblem() {
        Patient patient = new Patient();
        MedicalProblem medicalProblem = new MedicalProblem();
        patient.addProblem(medicalProblem);
        assertEquals(medicalProblem, patient.getProblem(medicalProblem));
    }

    @Test
    public void testAddProblem() {
        Patient patient = new Patient();
        MedicalProblem medicalProblem = new MedicalProblem();
        patient.addProblem(medicalProblem);
        assertEquals(medicalProblem, patient.getProblem(medicalProblem));
    }

    @Test
    public void testDeleteProblem() {
        Patient patient = new Patient();
        MedicalProblem medicalProblem = new MedicalProblem();
        patient.addProblem(medicalProblem);
        patient.deleteProblem(medicalProblem);
        assertTrue(patient.getAllProblems().isEmpty());
    }

    @Test
    public void testGetBodyLocation() {
        Patient patient = new Patient();
        BodyLocation bl = new BodyLocation();
        patient.addBodyLocation(bl);
        assertEquals(bl, patient.getBodyLocation(bl));
    }
    @Test
    public void testDeleteBodyLocation() {
        Patient patient = new Patient();
        BodyLocation bodyLocation = new BodyLocation();
        patient.addBodyLocation(bodyLocation);
        patient.deleteBodyLocation(bodyLocation);
        assertNull(patient.getBodyLocation(bodyLocation));
    }

    @Test
    public void testAddBodyLocation() {
        Patient patient = new Patient();
        BodyLocation bodyLocation = new BodyLocation();
        patient.addBodyLocation(bodyLocation);
        assertEquals(bodyLocation, patient.getBodyLocation(bodyLocation));
    }

    @Test
    public void testGetAllRecords() {
        ArrayList<Record> recordList = new ArrayList<>();
        Record record = new Record();
        recordList.add(record);
        Patient patient = new Patient();
        patient.addRecord(record);
        assertEquals(recordList, patient.getAllRecords());
    }

    @Test
    public void testAddRecord() {
        Patient patient = new Patient();
        Record record = new Record();
        Integer rID = record.getID();
        patient.addRecord(record);
        assertEquals(record, patient.getRecord(rID));
    }

    @Test
    public void testRemoveRecord() {
        Patient patient = new Patient();
        Record record = new Record();
        Integer rID = record.getID();
        patient.addRecord(record);
        assertNull(patient.getRecord(rID));
    }

    @Test
    public void testGetRecord() {
        Patient patient = new Patient();
        Record record = new Record();
        Integer rID = record.getID();
        patient.addRecord(record);
        assertEquals(record.getID(), patient.getRecord(rID).getID());
    }
}
