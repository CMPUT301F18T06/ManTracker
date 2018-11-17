package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import project.ece301.mantracker.Account.Username;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.loadFromFile;
import static project.ece301.mantracker.File.StoreData.patients;
import static project.ece301.mantracker.File.StoreData.saveInFile;


public class MainActivity extends AppCompatActivity{


    public static final String EXTRA_MESSAGE = "com.example.aman.aanand_feelsbook.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = -1;


                try{
                    Patient patient = new Patient();
                    patient.setUsername(new Username("KaranvirGidda"));
                    patients.add(patient);

                    index = patients.indexOf(patient);

                }catch (Exception e){
                }

                saveInFile(getApplicationContext());
                Toast.makeText(MainActivity.this, "Index =" + index, Toast.LENGTH_SHORT).show();

                Intent problem_list_switch = new Intent(getApplicationContext(), ProblemListActivity.class);
                problem_list_switch.putExtra(EXTRA_MESSAGE,Integer.toString(index));
                startActivity(problem_list_switch);
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile(this);
    }



    public void GoToSkeleton(View view){
        startActivity(new Intent(this, BodyLocationActivity.class));
    }
}
