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
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;

import static project.ece301.mantracker.File.StoreData.patients;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.Decode;

public class RecordPhotos extends AppCompatActivity {

    ArrayList<String> images = new ArrayList<String>();

    int index;
    int problemIndex;
    int recordIndex;
    int current_image = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_photos);

        Intent intent = getIntent();
        index = intent.getExtras().getInt("USERINDEX");
        problemIndex = intent.getExtras().getInt("ProblemIndex");
        recordIndex = intent.getExtras().getInt("RecordIndex");

        current_image = 0;
        images.clear();

        Record record = patients.get(index).getProblem(problemIndex).getRecord(recordIndex);
        ArrayList<String> photos = record.getPhotoList();
        for(int i=0; i<photos.size(); i++){
            images.add(photos.get(i));
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
        imageView.setImageBitmap(Decode(images.get(current_image)));

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
                        }
                    }else if(x<ImageWidth/2){
                        if(current_image > 0){
                            current_image--;
                            imageView.setImageBitmap(Decode(images.get(current_image)));
                        }
                    }
                }
                return true;
            }
        });
    }
}
