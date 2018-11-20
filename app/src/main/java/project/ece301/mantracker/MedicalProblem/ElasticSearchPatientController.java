package project.ece301.mantracker.MedicalProblem;

import android.os.AsyncTask;
import android.util.Log;


import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import project.ece301.mantracker.User.Patient;


/**
 * Created by romansky on 10/20/16.
 */
public class ElasticSearchPatientController {
    private static JestDroidClient client;

    // TODO we need a function which adds records to elastic search
    public static class AddPatientTask extends AsyncTask<Patient, Void, Void> {

        @Override
        protected Void doInBackground(Patient... patients) {
            verifySettings();
            Patient patient = patients[0];

            Index index = new Index.Builder(patient).index("cmput301f18t06test").type("patient").build();

            try {
                // where is the client?
                DocumentResult result = client.execute(index);

                if (result.isSucceeded()) {
                    Log.i("AddPatientTask", "POSTED successfully");
                }
                else {
                    Log.i("ElasticError", "Elasticsearch was not able to add the patient");
                }
            }
            catch (Exception e) {
                Log.i("AddPatientTask", "The application failed to build and send the patient");
            }


            return null;
        }
    }

    // TODO we need a function which gets tweets from elastic search
    public static class GetPatientTask extends AsyncTask<String, Void, ArrayList<Patient>> {
        @Override
        protected ArrayList<Patient> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Patient> patients = new ArrayList<Patient>();

            // TODO Build the query

            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String query = "{ \"size\": 10, \n" +
                    "    \"query\" : {\n" +
                    "        \"match\" : { \"Username\" : \"" + search_parameters[0] + "\" }\n" +
                    "    }\n" +
                    "}" ;

            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t06test")
                    .addType("patient")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    @SuppressWarnings("deprecation")
                    List<Patient> foundPatients = result.getSourceAsObjectList(Patient.class);
                    patients.addAll(foundPatients);
                }
                else {
                    Log.i("AddPatientTask", "The search query failed to find any records that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return patients;
        }
    }




    public static void verifySettings() {

        DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
        DroidClientConfig config = builder.build();

        JestClientFactory factory = new JestClientFactory();
        factory.setDroidClientConfig(config);
        client = (JestDroidClient) factory.getObject();

    }
}