package project.ece301.mantracker.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import project.ece301.mantracker.R;


public class AddRecordActivity extends AppCompatActivity {


    private static int REQUEST_CODE = 1;

    // Storage Permissions
    private static String[] STORAGE_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        ImageView imageView = findViewById(R.id.imageView);
        final TextView textView = findViewById(R.id.text_coord);

        imageView.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("SetTextI18n")

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    textView.setText("Touch coordinates : " +
                            String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                }
                return true;
            }
        });

    }

    /*
    Upload Image Click Event
    */
    public void UploadPhoto(View view){

        CheckPermissions(this);

        Intent upPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(upPhoto, REQUEST_CODE);
    }

    /*
    Action Upload Image
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && null != data) {
            Uri imageSelected = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(imageSelected ,
                    filePath, null, null, null);
            cursor.moveToFirst();

            int column_no = cursor.getColumnIndex(filePath[0]);
            String picturePath = cursor.getString(column_no);
            cursor.close();

            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    /*
    Check if you have the right permissions
    */
    public static void CheckPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // if we don't have permissions ten ask from the user
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    STORAGE_PERMISSIONS, REQUEST_CODE);
        }
    }

}
