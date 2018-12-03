package project.ece301.mantracker.MedicalProblem;

import android.os.AsyncTask;
import android.util.Log;


import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.Update;


public class ElasticSearchProblemController {
    private static JestDroidClient client;

    // TODO we need a function which adds records to elastic search
    public static class AddProblemTask extends AsyncTask<MedicalProblem, Void, Void> {

        @Override
        protected Void doInBackground(MedicalProblem... problems) {
            verifySettings();
            MedicalProblem problem = problems[0];

            Index index = new Index.Builder(problem).index("cmput301f18t06test").type("problem").id(problem.getId()).build();
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

    // TODO we need a function which gets Problems from elastic search
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



    // TODO we need a function which adds records to elastic search
    public static class DeleteProblemTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... parameter) {
            verifySettings();



            Delete delete = new Delete.Builder(parameter[0])
                    .index("cmput301f18t06test")
                    .type("problem")
                    .build();
//            Log.i("DeleteProblemTask", "Title: " + problem.getTitle());
//            Log.i("DeleteProblemTask", "Description: " + problem.getDescription());
//            Log.i("DeleteProblemTask", delete.getURI());
//            Log.i("DeleteProblemTask", "Date: " + problem.getDate());

            try {
                // where is the client?
                DocumentResult result = client.execute(delete);

                if (result.isSucceeded()) {
                    Log.i("DeleteblemTask", "DELETED successfully");
                    return "1";

                }
                else {
                    Log.i("ElasticError", "Elasticsearch was not able to add the record");
                }
            }
            catch (Exception e) {
                Log.i("AddProblemTask", "The application failed to build and send the problems");
            }


            return "0";
        }
    }

//    // TODO we need a function which adds records to elastic search
//    public static class UpdateProblemTask extends AsyncTask<MedicalProblem, Void, Void> {
//
//        @Override
//        protected Void doInBackground(MedicalProblem... problems,String...string) {
//            verifySettings();
//            MedicalProblem problem = problems[0];
//
//
//            String script = "{\n" +
//                    "  \"doc\": { \"apple_number\": " + "\"" +problem.get + "\", " +
//                    "\"apple_type\": " + "\"" + update_parameters[1] + "\"}" +
//                    "\n}";
//
//
//            Delete delete = new Delete.Builder(problem.getId())
//                    .index("cmput301f18t06test")
//                    .type("problem")
//                    .build();
//            Log.i("DeleteProblemTask", "Title: " + problem.getTitle());
//            Log.i("DeleteProblemTask", "Description: " + problem.getDescription());
//            Log.i("DeleteProblemTask", delete.getURI());
//            Log.i("DeleteProblemTask", "Date: " + problem.getDate());
//
//            try {
//                // where is the client?
//                DocumentResult result = client.execute(delete);
//
//                if (result.isSucceeded()) {
//                    Log.i("DeleteblemTask", "DELETED successfully");
//
//                }
//                else {
//                    Log.i("ElasticError", "Elasticsearch was not able to add the record");
//                }
//            }
//            catch (Exception e) {
//                Log.i("AddProblemTask", "The application failed to build and send the problems");
//            }
//
//
//            return null;
//        }
//    }
//






    public static class UpdateProblemQuery extends AsyncTask<String, Void, String> {
        /* Will be used when searching */
        @Override
        protected String doInBackground(String... update_parameters) {
            verifySettings();

            String script = "{\n" +
                    "  \"doc\": { \"title\": " + "\"" +update_parameters[1] + "\", " +
                    "\"description\": " + "\"" + update_parameters[2] + "\"}" +
                    "\n}";

            Update update = new Update.Builder(script)
                    .index("cmput301f18t06test")
                    .type("problem")
                    .id(update_parameters[0])
                    .build();


            try {
                // TODO get the results of the query
                DocumentResult result = client.execute(update);
                if (result.isSucceeded()){
                    Log.i("updateProblemsTask", update_parameters[0]+"  "+update_parameters[1]
                            +update_parameters[2]+"  " + "updated");
                    return "1";

                }
                else {
                    Log.i("updateProblemsTask", "The script failed to update "+ update_parameters[0]);
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return "0";
        }
    }







//    // TODO we need a function which gets tweets from elastic search
//    public static class AppleQuery extends AsyncTask<String, Void, String> {
//        /* Will be used when searching */
//        @Override
//        protected String doInBackground(String... search_parameters) {
//            verifySettings();
//
//            String info = new String();
//
//            // TODO Build the query
//
//            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
//            String query = "{ \"size\": 1, \n" +
//                    "    \"query\" : {\n" +
//                    "        \"match\": { \"apple_type\" : \"" + search_parameters[0] + "\"}\n " +
//                    "    }\n" +
//                    "}" ;
//
//            Search search = new Search.Builder(query)
//                    .addIndex("cmput301f18t06test")
//                    .addType("apple")
//                    .build();
//
//            try {
//                // TODO get the results of the query
//                SearchResult result = client.execute(search);
//                if (result.isSucceeded()){
//
//                    info = result.getJsonString();
//                    Log.i("AddProblemsTask", info);
//                }
//                else {
//                    Log.i("AddProblemsTask", "The search query failed to find any records that matched");
//                }
//            }
//            catch (Exception e) {
//                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
//            }
//
//            return info;
//        }
//    }


//
//    public static class DeleteAppleQuery extends AsyncTask<String, Void, String> {
//        /* Will be used when searching */
//        @Override
//        protected String doInBackground(String... deleted_parameters) {
//            verifySettings();
//
//            Delete delete = new Delete.Builder(deleted_parameters[0])
//                    .index("cmput301f18t06test")
//                    .type("apple")
//                    .build();
//
//
//            try {
//                // TODO get the results of the query
//                DocumentResult result = client.execute(delete);
//                if (result.isSucceeded()){
//                    Log.i("AddProblemsTask", "AWdrwLNIkEvD9wxolrcT"+"deleted");
//                    return "1";
//                }
//                else {
//                    Log.i("AddProblemsTask", "The search query failed to find any records that matched");
//                }
//            }
//            catch (Exception e) {
//                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
//            }
//
//            return "0";
//        }
//    }

//    public static class UpdateAppleQuery extends AsyncTask<String, Void, String> {
//        /* Will be used when searching */
//        @Override
//        protected String doInBackground(String... update_parameters) {
//            verifySettings();
//
//            String script = "{\n" +
//                    "  \"doc\": { \"apple_number\": " + "\"" +update_parameters[2] + "\", " +
//                    "\"apple_type\": " + "\"" + update_parameters[1] + "\"}" +
//                    "\n}";
//
//            Update update = new Update.Builder(script)
//                    .index("cmput301f18t06test")
//                    .type("apple")
//                    .id(update_parameters[0])
//                    .build();
//
//
//            try {
//                // TODO get the results of the query
//                DocumentResult result = client.execute(update);
//                if (result.isSucceeded()){
//                    Log.i("updateProblemsTask", update_parameters[0]+"  "+update_parameters[1]
//                            +update_parameters[2]+"  " + "updated");
//                    return "1";
//                }
//                else {
//                    Log.i("updateProblemsTask", "The script failed to update "+ update_parameters[0]);
//                }
//            }
//            catch (Exception e) {
//                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
//            }
//
//            return "0";
//        }
//    }









    public static void verifySettings() {

        DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
        DroidClientConfig config = builder.build();

        JestClientFactory factory = new JestClientFactory();
        factory.setDroidClientConfig(config);
        client = (JestDroidClient) factory.getObject();

    }
}