package project.ece301.mantracker.CareProviderHome;

import android.content.Context;
import android.util.Log;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

import project.ece301.mantracker.Account.Account;
import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.DataManagment.DataManager;
import project.ece301.mantracker.DataManagment.LocalStorage;
import project.ece301.mantracker.MedicalProblem.ElasticSearchProblemController;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.Presenter;
import project.ece301.mantracker.User.CareProvider;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;

public class CareProviderHomePresenter extends Presenter {
    private CareProviderHomeView careProviderHomeView;
    private DataManager dataManager;

    public CareProviderHomePresenter(Context context, CareProviderHomeView careProviderHomeView) {
        super(context);
        this.careProviderHomeView = careProviderHomeView;
        dataManager = DataManager.getInstance(context);
        careProviderHomeView.update();
    }

    public void onDestroy() {
        careProviderHomeView = null;
    }




    public void addPatient(String username) {
        Account account = dataManager.getUser(username);
        populateUserProblems(dataManager.getPatients().size()-1);
        if (account == null)
            careProviderHomeView.showNoPatientToast(username);
        else if (dataManager.getPatients().contains(account)) {
            careProviderHomeView.showAlreadyAddedPatientToast(username);
        } else if (account instanceof Patient) {
            dataManager.addPatient((Patient) account);
            careProviderHomeView.showAddedPatientToast(username);
            careProviderHomeView.update();
        } else if (account instanceof CareProvider) {
            careProviderHomeView.showNoPatientToast(username);
        } else {
            careProviderHomeView.showNoPatientToast(username);
        }

    }

    /**
     * Updates problems from ES. Should not be here
     * @param index
     */
    private void populateUserProblems(int index){

        try {
            //try to get from elasticsearch first. If not able, grab locally
            ElasticSearchProblemController.GetProblemsTask getProblemsTask = new ElasticSearchProblemController.GetProblemsTask();
            getProblemsTask.execute(dataManager.getPatient(index).getID());
            Log.d("PATIENT", dataManager.getPatient(index).getID());

            try {
                List<MedicalProblem> foundProblems = getProblemsTask.get();
                Log.i("ELASTICSEARCH", "WORKS SUCCESSFULLY FOR PROBLEMs");
                dataManager.setProblems(index, foundProblems);
                Log.i("ELASTICSEARCH", foundProblems.toString());
                LocalStorage.saveLoginSession(context, dataManager.getLoggedInUser());

            } catch (Exception e) {
                Log.i("AddProblemTask", "Failed to get the records from the async object");
            }
            Log.i("PATIENTHOME", "NOTIFIED");
        } catch (Exception e) {
//            //What is med_problem for? None longer matters as already loaded in dataManager
//            ArrayList<MedicalProblem> med_problem = new ArrayList<MedicalProblem>();
//            med_problem=patients.get(index).getAllProblems();
//            Log.i("PATIENTHOME", "SHOULDNOTREACH");
//            for(int i=0;i<med_problem.size();i++){
//                problems.add(med_problem.get(i));
//            }
        }
    }

    public Patient getPatientAt(int i) {
        return dataManager.getPatientAt(i);
    }

    public int getPatientProblemCount(int i) {
        return dataManager.getPatientProblemCount(i);
    }

    public int getPatientCount() {
        return dataManager.getPatientCount();
    }
}
