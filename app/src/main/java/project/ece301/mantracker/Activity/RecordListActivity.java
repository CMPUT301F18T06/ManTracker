package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;

public class RecordListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_records);
        //contains all records associated with problem NOTE this will change when we merge with PROBLEM
        ArrayList<Record> recordList = new ArrayList<Record>();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //update the record listview
        //get the record
    }

    public void toAddRecordActivity(View view) {
        //Move to add record activity
        Intent intent = new Intent(RecordListActivity.this, AddRecordActivity.class);
        startActivity(intent);
    }

}
