package project.ece301.mantracker.Activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import project.ece301.mantracker.R;

public class BodyLocationActivity extends AppCompatActivity {

    // DELTE IT LATER // PLACE HOLDER VARIABLES
    String BodyLocation;
    String Coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_location);
    }

    @SuppressLint("ClickableViewAccessibility")

    @Override
    public void onStart() {
        super.onStart();

        final ImageView imageView= findViewById(R.id.iSkeleton);
        final TextView textView = findViewById(R.id.resultsView);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    float x = Float.parseFloat(String.valueOf(event.getX()));
                    float y = Float.parseFloat(String.valueOf(event.getY()));
                    String location = "x: " +
                            String.valueOf(x + ", y: " + y);
                    textView.setText(location);


                    // Fix the spacing between the image and the skeleton
                    final int SkeletonYstartsAt = 35;
                    final int SkeletonXstartAt  = 26;

                    // set the cursor
                    ImageView cursor = findViewById(R.id.Cursor);
                    cursor.setVisibility(View.VISIBLE);
                    cursor.setX(imageView.getLeft() + x - SkeletonXstartAt);
                    cursor.setY(imageView.getTop() + y - SkeletonYstartsAt);

                    // set coordinates of the final cursor position
                    Coordinates = x + ":" + y ;
                }
                return true;
            }
        });
    }

    private void checkCoordinates(String point){
        float x = Float.parseFloat(point.split(":")[0]);
        float y = Float.parseFloat(point.split(":")[1]);

        TextView textView = findViewById(R.id.resultsView);
        textView.setText("" + x + ", " + y);

        // Upper Body
        if( (x>560.0 && x<880.0) && (y>300.0 && y<600.0) ){
            textView.setText("Upper Body");
            BodyLocation="upper body";
        }

        // Middle Body
        else if( (x>580.0 && x<890.0) && (y>600.001 && y<900.0) ){
            textView.setText("middle body");
            BodyLocation="middle body";
        }

        // Left Thigh
        else if( (x>550.0 && x<720.0) && (y>900.001 && y<1260.0) ){
            textView.setText("left thigh");
            BodyLocation="left thigh";
        }

        // Right Thigh
        else if( (x>740.0 && x<900.0) && (y>900.001 && y<1260.0) ){
            textView.setText("right thigh");
            BodyLocation="right thigh";
        }

        // Left Knee
        else if( (x>570.0 && x<720.0) && (y>1260.001 && y<1460.0) ){
            textView.setText("left knee");
            BodyLocation="left knee";
        }

        // Right Knee
        else if( (x>750.0 && x<870.0) && (y>1260.001 && y<1460.0) ){
            textView.setText("right knee");
            BodyLocation="right knee";
        }

        // Left leg
        else if( (x>570.0 && x<720.0) && (y>1460.001 && y<1770.0) ){
            textView.setText("left leg");
            BodyLocation="left leg";
        }

        // Right leg
        else if( (x>750.0 && x<870.0) && (y>1460.001 && y<1770.0) ){
            textView.setText("right leg");
            BodyLocation="right leg";
        }

        // Left arm
        else if( (x>450.0 && x<559.99) && (y>320.0 && y<640.0) ){
            textView.setText("left arm");
            BodyLocation="left arm";
        }

        // Right arm
        else if( (x>880.01 && x<980.0) && (y>320.00 && y<640.0) ){
            textView.setText("right arm");
            BodyLocation="right arm";
        }

        // Left elbow
        else if( (x>430.0 && x<530.0) && (y>640.01 && y<740.0) ){
            textView.setText("left elbow");
            BodyLocation="left elbow";
        }

        // Right elbow
        else if( (x>900.0 && x<1000.0) && (y>640.01 && y<740.0) ){
            textView.setText("right elbow");
            BodyLocation="right elbow";
        }

        // left foreArm
        else if( ((x>390.0 && x<510.0) && (y>740.01 && y<820.0)) ||
                    ((x>370.0 && x<470.0) && (y>820.01 && y<950.0)) ){
            textView.setText("left foreArm");
            BodyLocation="left forearm";
        }

        // Right foreArm
        else if( ((x>920.0 && x<1020.0) && (y>740.01 && y<820.0)) ||
                ((x>950.0 && x<1050.0) && (y>820.01 && y<950.0)) ){
            textView.setText("right foreArm");
            BodyLocation="right forearm";
        }
        else{
            BodyLocation="No Match";
        }


    }

    public void SaveButtoonClick(View view){
        RadioGroup frontOrBackRadio = findViewById(R.id.rgFrontOrBack);

        RadioButton radioButton;

        int selected = frontOrBackRadio.getCheckedRadioButtonId();
        radioButton = findViewById(selected);
        CharSequence result = radioButton.getText();
//        Toast.makeText(BodyLocationActivity.this, result, Toast.LENGTH_SHORT).show();

        checkCoordinates(Coordinates);
    }

}
