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
import io.searchbox.core.Update;
import project.ece301.mantracker.User.CareProvider;


/**
 * Created by romansky on 10/20/16.
 */
public class ElasticSearchCareproviderContoller{
    private static JestDroidClient client;

    // TODO we need a function which adds records to elastic search
    public static class AddCareProviderTask extends AsyncTask<CareProvider, Void, Void> {

        @Override
        protected Void doInBackground(CareProvider... careProviders) {
            verifySettings();
            CareProvider careprovider = careProviders[0];

            Index index = new Index.Builder(careprovider)
                    .index("cmput301f18t06test")
                    .type("careprovider")
                    .id(careprovider.getID())
                    .build();

            try {
                // where is the client?
                DocumentResult result = client.execute(index);

                if (result.isSucceeded()) {
                    Log.i("AddCareProviderTask", "POSTED successfully");
                }
                else {
                    Log.i("ElasticError", "Elasticsearch was not able to add the CareProvider");
                }
            }
            catch (Exception e) {
                Log.i("AddCPTask", "The application failed to build and send the CareProvider");
            }


            return null;
        }
    }

    public static class GetCareProviderTask extends AsyncTask<String, Void, ArrayList<CareProvider>> {
        @Override
        protected ArrayList<CareProvider> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<CareProvider> careproviders = new ArrayList<CareProvider>();

            // TODO Build the query

            //String query = "{ \"size\": 3, \"query\" : { \"term\" : { \"message\" : \""+ search_parameters[0] + "\"}}}";
            String query = "{ \"size\": 10, \n" +
                    "    \"query\" : {\n" +
                    "        \"match\" : { \"username\" : \"" + search_parameters[0] + "\" }\n" +
                    "    }\n" +
                    "}" ; //{"username":"buggedUser"}

            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t06test")
                    .addType("careprovider")
                    .build();

            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if (result.isSucceeded()){
                    @SuppressWarnings("deprecation")
                    List<CareProvider> foundCareproviders = result.getSourceAsObjectList(CareProvider.class);
                    careproviders.addAll(foundCareproviders);
                }
                else {
                    Log.i("AddCareprovderTask", "The search query failed to find any records that matched");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return careproviders;
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