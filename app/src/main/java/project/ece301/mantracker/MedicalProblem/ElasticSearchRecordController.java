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


/**
 * Created by romansky on 10/20/16.
 */
public class ElasticSearchRecordController {
    private static JestDroidClient client;

    // TODO we need a function which adds records to elastic search
    public static class AddRecordTask extends AsyncTask<Record, Void, Void> {

        @Override
        protected Void doInBackground(Record... records) {
                verifySettings();
                Record record = records[0];

                Index index = new Index.Builder(record).index("cmput301f18t06test").type("record").build();
                Log.i("AddRecordTask", "Title: " + record.getTitle());
                Log.i("AddRecordTask", "Description: " + record.getDescription());
                Log.i("AddRecordTask", index.getURI());
                Log.i("AddRecordTask", "POSTEDID: " + record.getProblemID());
             Log.i("AddRecordTask", "Date: " + record.getDate());

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);

                    if (result.isSucceeded()) {
                        Log.i("AddRecordTask", "POSTED successfully");
                    }
                    else {
                        Log.i("ElasticError", "Elasticsearch was not able to add the record");
                    }
                }
                catch (Exception e) {
                    Log.i("AddRecordTask", "The application failed to build and send the records");
                }


            return null;
        }
    }

    // TODO we need a function which gets tweets from elastic search
    public static class GetRecordsTask extends AsyncTask<String, Void, ArrayList<Record>> {
        @Override
        protected ArrayList<Record> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Record> records = new ArrayList<Record>();

            // TODO Build the query

            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String query = "{ \"size\": 100, \n" +
                    "    \"query\" : {\n" +
                    "        \"match\" : { \"associatedProblemID\" : \"" + search_parameters[0] + "\" }\n" +
                    "    }\n" +
                    "}" ;

            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t06test")
                    .addType("record")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    @SuppressWarnings("deprecation")
                    List<Record> foundRecords = result.getSourceAsObjectList(Record.class);
                    records.addAll(foundRecords);
                }
                else {
                    Log.i("AddRecordTask", "The search query failed to find any records that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return records;
        }
    }

    // TODO we need a function which gets tweets from elastic search
    public static class UserQuery extends AsyncTask<String, Void, ArrayList<Record>> {
        @Override
        protected ArrayList<Record> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Record> records = new ArrayList<Record>();

            // TODO Build the query

            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String query = "{ \"size\": 100, \n" +
                    "    \"query\" : {\n" +
                    "        \"multi_match\": { \"query\" : \"" + search_parameters[0] + "\",\n " +
                    "\"fields\": [ \"Title\", \"Description\", \"locationName\", \"bodyLocations\"] }\n" +
                    "    }\n" +
                    "}" ;
            Log.i("searchable", "Query is: " + query);
            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t06test")
                    .addType("record")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    @SuppressWarnings("deprecation")
                    List<Record> foundRecords = result.getSourceAsObjectList(Record.class);
                    records.addAll(foundRecords);
                }
                else {
                    Log.i("AddRecordTask", "The search query failed to find any records that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return records;
        }
    }


    public static class GetRecordsWithID extends AsyncTask<String, Void, ArrayList<Record>> {
        @Override
        protected ArrayList<Record> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Record> records = new ArrayList<Record>();


            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String query = "{ \"size\": 10, \n" +
                    "    \"query\" : {\n" +
                    "        \"match\" : { \"ID\" : \"" + search_parameters[0] + "\" }\n" +
                    "    }\n" +
                    "}" ;

            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t06test")
                    .addType("record")
                    .build();

            try {

                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    @SuppressWarnings("deprecation")
                    List<Record> foundRecords = result.getSourceAsObjectList(Record.class);
                    records.addAll(foundRecords);
                }
                else {
                    Log.i("AddRecordTask", "The search query failed to find any records that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");

            }

            return records;
        }
    }

    public static class GetAllRecords extends AsyncTask<String, Void, ArrayList<Record>> {
        // Get all the records
        @Override
        protected ArrayList<Record> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Record> records = new ArrayList<Record>();


            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String query = "{ \"size\": 3, \"query\" : { \"match_all\" : {}}}";

            Log.i("RecordLabelTask", "Query: " + query);

            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t06test")
                    .addType("record")
                    .build();

            try {

                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    @SuppressWarnings("deprecation")
                    List<Record> foundRecords = result.getSourceAsObjectList(Record.class);
                    records.addAll(foundRecords);
                }
                else {
                    Log.i("RecordLabelTask", "The search query failed to find any records that matched");
                }
            }
            catch (Exception e) {
                Log.i("RecordLabelTask", "Something went wrong when we tried to communicate" +
                        " with the elasticsearch server!");

            }

            return records;
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