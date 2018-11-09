package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;

public class RecordListActivity extends AppCompatActivity {
    ListView recordListView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_records);
        recordListView = findViewById(R.id.recordList);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //contains all records associated with problem NOTE this will change when we merge with MEDICAL PROBLEM
        ArrayList<Record> recordList = new ArrayList<Record>();
        //update the record listview
        //get the record

        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, );
        //recordListView.setAdapter(adapter);
    }

    public void toAddRecordActivity(View view) {
        //Move to add record activity
        Intent intent = new Intent(RecordListActivity.this, AddRecordActivity.class);
        startActivity(intent);
    }

}
