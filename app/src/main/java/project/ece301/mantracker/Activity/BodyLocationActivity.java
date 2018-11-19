package project.ece301.mantracker.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;

import project.ece301.mantracker.R;

import static project.ece301.mantracker.MedicalProblem.UploadPhoto.CheckPermissionsCamera;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.CheckPermissionsGallery;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.Decode;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.Encode;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.UploadFromCamera;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.UploadFromGallery;

public class BodyLocationActivity extends AppCompatActivity {

    String Coordinates;
//    boolean cameraPermission = false;
//    boolean galleryPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_location);
    }

    @SuppressLint("ClickableViewAccessibility")

    @Override
    public void onStart() {
        super.onStart();

        final ImageView imageView= findViewById(R.id.image_BL);
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

                    // set the cursor
                    ImageView cursor = findViewById(R.id.cursor);
                    cursor.setVisibility(View.VISIBLE);

                    cursor.setX(imageView.getLeft() + x );
                    cursor.setY(imageView.getTop() + y );

                    // set coordinates of the final cursor position
                    Coordinates = x + ":" + y ;

                }
                return true;
            }
        });
    }

    public void CameraPhoto(View view){
        CheckPermissionsCamera(this);
    }

    public void GalleryPhoto(View view){
        CheckPermissionsGallery(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    UploadFromCamera(this);
                } else {
                    // user denied the message
                    Button cameraButton = findViewById(R.id.camera_BL);
                    cameraButton.setEnabled(false);
                }
                break;
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    UploadFromGallery(this);
                } else {
                    // user denied the message
                    Button galleryButton = findViewById(R.id.gallery_BL);
                    galleryButton.setEnabled(false);
                }
                break;
        }
    }

    /*
    Action Upload Image
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        ImageView imageView_BL = findViewById(R.id.image_BL);
        ImageView CursorImage = findViewById(R.id.cursor);

        CursorImage.setX(imageView_BL.getLeft() );
        CursorImage.setY(imageView_BL.getTop() );

        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

            try {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                imageView_BL.setImageBitmap(image);
                imageView_BL.setVisibility(View.VISIBLE);

                // make the cursor visible
                CursorImage.setVisibility(View.VISIBLE);
            }
            catch(NullPointerException ex){
                Toast.makeText(this, "Unable to show photo. Please try again.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        else if (requestCode == 2 && resultCode == RESULT_OK && null != data) {

            Uri imageSelected = data.getData();

            try{

                Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(imageSelected));

                /* For encoding and decoding //

                String Base64Image = Encode(bitmap, Bitmap.CompressFormat.JPEG, 100);
                Bitmap decodedImage = Decode(Base64Image);
                imageView_BL.setImageBitmap(decodedImage);

                */

                // set the image
                imageView_BL.setImageBitmap(bitmap);
                imageView_BL.setVisibility(View.VISIBLE);

//              make the cursor visible
                CursorImage.setVisibility(View.VISIBLE);

            }
            catch (NullPointerException ex){
                Toast.makeText(this, "Unable to show photo. Please try again.",
                        Toast.LENGTH_SHORT).show();
            }
            catch(FileNotFoundException ex){
                Toast.makeText(this, "Unable to show photo. Please try again.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void SaveButtonClick(View view){

        // Save the Photo and the point location in the File
        // TODO:

        // Back to the Add Record Screen where User can Add more Photos
        finish();
    }


}
