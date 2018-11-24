package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import project.ece301.mantracker.R;

public class clickedimg extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickedimg);
        Intent intent = getIntent();
        TextView txt = (TextView) findViewById(R.id.clickedimginfo);
        ImageView img = (ImageView) findViewById(R.id.clickedimgview);
        Integer img_id = Integer.parseInt(intent.getStringExtra("imgid"));
        String img_info = intent.getStringExtra("imgtitle");
        img.setImageResource(img_id);
        txt.setText(img_id);

    }
}