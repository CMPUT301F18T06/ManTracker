package project.ece301.mantracker.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import project.ece301.mantracker.R;

public class BodyLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_location);
    }

    @Override
    public void onStart() {
        super.onStart();

        PhotoView photoView = (PhotoView) findViewById(R.id.iSkeleton);
        photoView.setImageResource(R.drawable.skeleton);

//        ImageView imageView = findViewById(R.id.imageView);
        final TextView textView = findViewById(R.id.text_coord);


    }

}
