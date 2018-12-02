package project.ece301.mantracker.Edit_MedPro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import project.ece301.mantracker.MedicalProblem.ElasticSearchProblemController;
import project.ece301.mantracker.R;

public class edit_medpro extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medpro);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar edit_medpro_toolbar = (Toolbar) findViewById(R.id.edit_medpro_toolbar);
        EditText edit_medpro_title = (EditText) findViewById(R.id.edit_medpro_title);
        EditText edit_medpro_descri = (EditText) findViewById(R.id.edit_medpro_descri);
        AppCompatButton edit_medpro_save = (AppCompatButton) findViewById(R.id.edit_medpro_save);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String med_protitle = extras.getString("PROTITLE");
        String med_prodescription = extras.getString("PRODESCRI");
        String med_proId = extras.getString("PROBLEMID");

//        edit_medpro_toolbar.setNavigationOnClickListener( new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });



        setSupportActionBar(edit_medpro_toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(med_protitle);

        edit_medpro_title.setText(med_protitle);
        edit_medpro_descri.setText(med_prodescription);

        edit_medpro_save.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                String jsoninfo = new String();

                String modified_title = edit_medpro_title.getText().toString();
                String modified_description = edit_medpro_descri.getText().toString();
                Log.i("medid",med_proId);
                Log.i("medtitle",modified_title);
                Log.i("meddescription",modified_description);


                ElasticSearchProblemController.UpdateProblemQuery updateproblemquery = new ElasticSearchProblemController.UpdateProblemQuery();
                updateproblemquery.execute(med_proId,modified_title,modified_description);

                try {
                    String status = updateproblemquery.get();
                    if (status.equals("1")){

                        Toast.makeText(edit_medpro.this, "updated Succeed add any fucniton here", Toast.LENGTH_LONG).show();
                        finish();

                    }else if (status.equals("0")){
                        Toast.makeText(edit_medpro.this, "updated Failed add any funciton here", Toast.LENGTH_LONG).show();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });



    }
//    private static JestDroidClient client;
//
//    public void StoreToElasticSearch(String title , String description){
//        verifySettings();
//        String query = "{ \"size\": 1, \n" +
//                "    \"query\" : {\n" +
//                "        \"match\" : { \"apple_number\" : \"" + "apple number 1" + "\" }\n" +
//                "    }\n" +
//                "}" ;
//
//        Search search = new Search.Builder(query)
//                .addIndex("cmput301f18t06test")
//                .addType("apple")
//                .build();
//
//        try {
//            // TODO get the results of the query
//            SearchResult result = client.execute(search);
//            if (result.isSucceeded()){
//                Log.d("apple", "StoreToElasticSearch:"+ result);
//            }
//            else {
//                Log.i("AddPatientTask", "The search query failed to find any records that matched");
//            }
//        }
//        catch (Exception e) {
//            Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
//        }
//
//    }
//
//
//    public static void verifySettings() {
//
//        DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
//        DroidClientConfig config = builder.build();
//
//        JestClientFactory factory = new JestClientFactory();
//        factory.setDroidClientConfig(config);
//        client = (JestDroidClient) factory.getObject();
//
//    }
}
