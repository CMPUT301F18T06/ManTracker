package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import project.ece301.mantracker.R;

public class BodyLocationActivity extends AppCompatActivity {
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_location);
    }

    @Override
    public void onStart() {
        super.onStart();

        PhotoView photoView = (PhotoView) findViewById(R.id.iSkeleton);
        Button nextLocationButton = (Button) findViewById(R.id.bConfirmLocation);
        final RadioGroup frontOrBackRadio = (RadioGroup) findViewById(R.id.rgFrontOrBack);

        photoView.setImageResource(R.drawable.skeleton);

//        ImageView imageView = findViewById(R.id.imageView);
//        final TextView textView = findViewById(R.id.text_coord);

        nextLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = frontOrBackRadio.getCheckedRadioButtonId();
                radioButton=(RadioButton)findViewById(selected);
                CharSequence result = radioButton.getText();
                Toast.makeText(BodyLocationActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
