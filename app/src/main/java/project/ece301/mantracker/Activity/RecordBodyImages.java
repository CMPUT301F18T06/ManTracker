package project.ece301.mantracker.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import project.ece301.mantracker.MedicalProblem.BodyLocation;
import project.ece301.mantracker.MedicalProblem.MedicalProblem;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;
import project.ece301.mantracker.User.Patient;

import static project.ece301.mantracker.File.StoreData.patients;
import static project.ece301.mantracker.File.StoreData.saveInFile;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.Decode;

public class RecordBodyImages extends AppCompatActivity {

    ArrayList<String> images = new ArrayList<String>();
    ArrayList<String> labels = new ArrayList<String>();
    int index, problemIndex, recordIndex;
    int current_image = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_body_images);

        Intent intent = getIntent();
        index = intent.getExtras().getInt("USERINDEX");
        problemIndex = intent.getExtras().getInt("ProblemIndex");
        recordIndex = intent.getExtras().getInt("RecordIndex");

        current_image = 0;

        Record record = patients.get(index).getProblem(problemIndex).getRecord(recordIndex);
        ArrayList<BodyLocation> bodyLocations = record.getBodyLocationList();
        for(int i=0; i<bodyLocations.size(); i++){
            images.add(bodyLocations.get(i).getBodyImage());
            labels.add(bodyLocations.get(i).getLabel());
        }

        if(images.size()==0){
            Toast.makeText(this, "No Images to Show", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onStart() {
        super.onStart();

        final ImageView imageView = findViewById(R.id.RecordBodyImage);
        final TextView textview = findViewById(R.id.RecordImageLabel);

        imageView.setImageBitmap(Decode(images.get(current_image)));
        textview.setText(labels.get(current_image));

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    // image width and height of drawable image
                    float ImageWidth = imageView.getMeasuredWidth();

                    // image View coordinates
                    float x = Float.parseFloat(String.valueOf(event.getX()));

                    if(x >= ImageWidth/2) {
                        if(current_image < images.size()-1){
                            current_image++;
                            imageView.setImageBitmap(Decode(images.get(current_image)));
                            textview.setText(labels.get(current_image));
                        }
                    }else if(x<ImageWidth/2){
                        if(current_image > 0){
                            current_image--;
                            imageView.setImageBitmap(Decode(images.get(current_image)));
                            textview.setText(labels.get(current_image));
                        }
                    }
                }
                return true;
            }
        });
    }

    private void DefaultState(){

        current_image = 0;

        Record record = patients.get(index).getProblem(problemIndex).getRecord(recordIndex);
        ArrayList<BodyLocation> bodyLocations = record.getBodyLocationList();
        for(int i=0; i<bodyLocations.size(); i++){
            images.add(bodyLocations.get(i).getBodyImage());
            labels.add(bodyLocations.get(i).getLabel());
        }

        if(images.size()==0){
            Toast.makeText(this, "No Images to Show", Toast.LENGTH_SHORT).show();
            finish();
        }

        ImageView imageView = findViewById(R.id.RecordBodyImage);
        TextView textview = findViewById(R.id.RecordImageLabel);

        imageView.setImageBitmap(Decode(images.get(current_image)));
        textview.setText(labels.get(current_image));
    }

    public void DeleteImage(View view){
        ImageView imageView = findViewById(R.id.RecordBodyImage);
        TextView textview = findViewById(R.id.RecordImageLabel);

        // remove the current image from the images list and offline/online
        images.remove(current_image);
        labels.remove(current_image);

        // deleting offline
        Patient patient = patients.get(index);
        MedicalProblem problem = patient.getProblem(problemIndex);

        Record record = problem.getRecord(recordIndex);
        record.deleteBodyLocation(current_image);

        int size = record.getBodyLocationList().size();

        Toast.makeText(this, "sizeimage:" + images.size() + "size:" + size , Toast.LENGTH_SHORT).show();

        problem.setRecord(recordIndex,record);
        patient.setProblem(problem,problemIndex);

        patients.set(index,patient);

        // save in file
        saveInFile(this);

        if(images.size()!=0){
            DefaultState();
        }
        else{
            Toast.makeText(this, "No Images to Show", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
