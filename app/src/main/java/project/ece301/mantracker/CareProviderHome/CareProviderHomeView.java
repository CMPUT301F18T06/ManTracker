package project.ece301.mantracker.CareProviderHome;

import project.ece301.mantracker.MedicalProblem.MedicalProblem;

public interface CareProviderHomeView {

    void update();

    void showNoSearchResults();

    void navigateToProblem(MedicalProblem problem);
}