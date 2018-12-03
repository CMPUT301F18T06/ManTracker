package project.ece301.mantracker.CareProviderHome;

import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.User.Patient;

public interface CareProviderHomeView {

    void update();

    void showNoSearchResults();
    void showNoPatientToast(String username);
    void showAddedPatientToast(String username);
    void showAlreadyAddedPatientToast(String username);

    void navigateToPatient(int index);
}