package project.ece301.mantracker.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import project.ece301.mantracker.R;

public class AvailableBodyLocationPhotos extends AppCompatActivity {

    ImageView PreviousImageView, NextImageView, image, image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_body_location_photos);

        Toast.makeText(this, "Got in", Toast.LENGTH_SHORT).show();

        PreviousImageView = findViewById(R.id.ivPrevious);
        NextImageView = findViewById(R.id.ivNext);
        image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.skeleton);

        image2 = findViewById(R.id.longClickTest);

        PreviousImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AvailableBodyLocationPhotos.this, "Previous Button Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        NextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AvailableBodyLocationPhotos.this, "Next Button Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        image2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(AvailableBodyLocationPhotos.this, "Long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
