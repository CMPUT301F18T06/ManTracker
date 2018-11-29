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


public class ElasticSearchProblemController {
    private static JestDroidClient client;

    // TODO we need a function which adds records to elastic search
    public static class AddProblemTask extends AsyncTask<MedicalProblem, Void, Void> {

        @Override
        protected Void doInBackground(MedicalProblem... problems) {
            verifySettings();
            MedicalProblem problem = problems[0];

            Index index = new Index.Builder(problem).index("cmput301f18t06test").type("problem").build();
            Log.i("AddProblemTask", "Title: " + problem.getTitle());
            Log.i("AddProblemTask", "Description: " + problem.getDescription());
            Log.i("AddProblemTask", index.getURI());
            Log.i("AddProblemTask", "Date: " + problem.getDate());

            try {
                // where is the client?
                DocumentResult result = client.execute(index);

                if (result.isSucceeded()) {
                    Log.i("AddProblemTask", "POSTED successfully");
                }
                else {
                    Log.i("ElasticError", "Elasticsearch was not able to add the record");
                }
            }
            catch (Exception e) {
                Log.i("AddProblemTask", "The application failed to build and send the problems");
            }


            return null;
        }
    }

    // TODO we need a function which gets tweets from elastic search
    public static class GetProblemsTask extends AsyncTask<String, Void, ArrayList<MedicalProblem>> {
        @Override
        protected ArrayList<MedicalProblem> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<MedicalProblem> problems = new ArrayList<MedicalProblem>();

            // TODO Build the query

            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String query = "{ \"size\": 10, \n" +
                    "    \"query\" : {\n" +
                    "        \"match\" : { \"associatedPatientID\" : \"" + search_parameters[0] + "\" }\n" +
                    "    }\n" +
                    "}" ;

            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t06test")
                    .addType("problem")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    @SuppressWarnings("deprecation")
                    List<MedicalProblem> foundProblems = result.getSourceAsObjectList(MedicalProblem.class);
                    problems.addAll(foundProblems);
                }
                else {
                    Log.i("AddProblemsTask", "The search query failed to find any records that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return problems;
        }
    }

    // TODO we need a function which gets tweets from elastic search
    public static class UserQuery extends AsyncTask<String, Void, ArrayList<MedicalProblem>> {
        /* Will be used when searching */
        @Override
        protected ArrayList<MedicalProblem> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<MedicalProblem> problems = new ArrayList<MedicalProblem>();

            // TODO Build the query

            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String query = "{ \"size\": 100, \n" +
                    "    \"query\" : {\n" +
                    "        \"multi_match\": { \"query\" : \"" + search_parameters[0] + "\",\n " +
                    "\"fields\": [ \"title\", \"description\"] }\n" +
                    "    }\n" +
                    "}" ;

            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t06test")
                    .addType("problem")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    @SuppressWarnings("deprecation")
                    List<MedicalProblem> foundProblems = result.getSourceAsObjectList(MedicalProblem.class);
                    problems.addAll(foundProblems);
                }
                else {
                    Log.i("AddProblemsTask", "The search query failed to find any records that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return problems;
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