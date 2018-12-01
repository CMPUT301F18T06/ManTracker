package project.ece301.mantracker.Edit_MedPro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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
                String modified_title = edit_medpro_title.getText().toString();
                String modified_description = edit_medpro_descri.getText().toString();

                StoreToElasticSearch(modified_title,modified_description);

            }
        });



    }

    public void StoreToElasticSearch(String title, String description){





    }
}
