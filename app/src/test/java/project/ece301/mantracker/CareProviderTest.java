package project.ece301.mantracker;

import org.junit.Test;

import java.util.ArrayList;

import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CareProviderTest {

    @Test
    public void testGetPatient() {
        ArrayList<Patient> patients = new ArrayList<>();
        Patient patient = new Patient();
        patients.add(patient);
        CareProvider careProvider = new CareProvider(patients);
        assertEquals(patient, careProvider.getPatient(patient));
    }

    @Test
    public void testAddPatient() {
        CareProvider careProvider = new CareProvider();
        Patient patient = new Patient();
        careProvider.addPatient(patient);
        assertEquals(patient, careProvider.getPatient(patient));
    }

    @Test
    public void testDeletePatient() {
        ArrayList<Patient> patients = new ArrayList<>();
        Patient patient = new Patient();
        patients.add(patient);
        CareProvider careProvider = new CareProvider(patients);
        careProvider.deletePatient(patient);
        assertFalse(careProvider.getPatientsList().contains(patient));
    }

    @Test
    public void testGetPatientsList() {
        ArrayList<Patient> patients = new ArrayList<>();
        Patient patient = new Patient();
        patients.add(patient);
        CareProvider careProvider = new CareProvider(patients);
        assertEquals(patients, careProvider.getPatientsList());
    }
}


