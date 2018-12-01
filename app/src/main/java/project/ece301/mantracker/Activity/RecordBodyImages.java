package project.ece301.mantracker.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
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
    ArrayList<String> Coordinates = new ArrayList<String>();
    int index, problemIndex, recordIndex;
    int current_image = 0;
    float ImageHeight,ImageWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_body_images);

        Intent intent = getIntent();
        index = intent.getExtras().getInt("USERINDEX");
        problemIndex = intent.getExtras().getInt("ProblemIndex");
        recordIndex = intent.getExtras().getInt("RecordIndex");

        current_image = 0;
        images.clear();
        labels.clear();
        Coordinates.clear();

        Record record = patients.get(index).getProblem(problemIndex).getRecord(recordIndex);
        ArrayList<BodyLocation> bodyLocations = record.getBodyLocationList();
        for(int i=0; i<bodyLocations.size(); i++){
            images.add(bodyLocations.get(i).getBodyImage());
            labels.add(bodyLocations.get(i).getLabel());
            Coordinates.add(bodyLocations.get(i).getImageCordinates());
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

        final ImageView imageView = findViewById(R.id.RecordPhoto);

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
                            setImage();
                        }
                    }else if(x<ImageWidth/2){
                        if(current_image > 0){
                            current_image--;
                            setImage();
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        setImage();
    }

    private void setImage(){

        final ImageView imageView = findViewById(R.id.RecordPhoto);
        final TextView textview = findViewById(R.id.RecordImageLabel);
        final ImageView cursor = findViewById(R.id.cursorRecordBodyImage);

        Bitmap bitmap = Decode(images.get(current_image));
        imageView.setImageBitmap(bitmap);
        textview.setText(labels.get(current_image));

        // image width and height of drawable image
        ImageHeight = imageView.getMeasuredHeight();
        ImageWidth = imageView.getMeasuredWidth();

        // bitmap width and height
        float BitmapWidth = bitmap.getWidth();
        float BitmapHeight = bitmap.getHeight();

        // actual coordinates of the pixel
        float WidthRatio = BitmapWidth / ImageWidth;
        float HeightRatio = BitmapHeight / ImageHeight;

        cursor.setVisibility(View.VISIBLE);

        cursor.setX(Float.parseFloat (Coordinates.get(current_image).split(":")[0]) / WidthRatio );
        cursor.setY(Float.parseFloat (Coordinates.get(current_image).split(":")[1]) / HeightRatio);

    }

    private void DefaultState(){

        current_image = 0;
        images.clear();
        labels.clear();
        Coordinates.clear();

        Record record = patients.get(index).getProblem(problemIndex).getRecord(recordIndex);
        ArrayList<BodyLocation> bodyLocations = record.getBodyLocationList();
        for(int i=0; i<bodyLocations.size(); i++){
            images.add(bodyLocations.get(i).getBodyImage());
            labels.add(bodyLocations.get(i).getLabel());
            Coordinates.add(bodyLocations.get(i).getImageCordinates());
        }

        if(images.size()!=0){
            setImage();
        }
        else{
            Toast.makeText(this, "No Images to Show", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void DeleteImage(View view){

        // remove the current image from the images list and offline/online
        images.remove(current_image);
        labels.remove(current_image);
        Coordinates.remove(current_image);

        // deleting offline
        Patient patient = patients.get(index);
        MedicalProblem problem = patient.getProblem(problemIndex);

        Record record = problem.getRecord(recordIndex);
        record.deleteBodyLocation(current_image);

        int size = record.getBodyLocationList().size();

        problem.setRecord(recordIndex,record);
        patient.setProblem(problem,problemIndex);

        patients.set(index,patient);

        // save in file
        saveInFile(this);

        if(size!=0){
            DefaultState();
        }
        else{
            Toast.makeText(this, "No Images to Show", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
