package project.ece301.mantracker.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import project.ece301.mantracker.MedicalProblem.BodyLocation;
import project.ece301.mantracker.R;

import static project.ece301.mantracker.MedicalProblem.UploadPhoto.CheckPermissionsCamera;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.CheckPermissionsGallery;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.Decode;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.Encode;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.UploadFromCamera;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.UploadFromGallery;

public class BodyLocationActivity extends AppCompatActivity {

    String Coordinates = null;
    String encodedImage = null;

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

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {

                    // image View coordinates
                    float x = Float.parseFloat(String.valueOf(event.getX()));
                    float y = Float.parseFloat(String.valueOf(event.getY()));

                    // set the cursor
                    ImageView cursor = findViewById(R.id.cursor);
                    cursor.setVisibility(View.VISIBLE);
                    cursor.setX(imageView.getLeft() + x );
                    cursor.setY(imageView.getTop() + y );

                    // image width and height of drawable image
                    float ImageWidth = imageView.getMeasuredWidth();
                    float ImageHeight = imageView.getMeasuredHeight();

                    // bitmap width and height
                    float BitmapWidth = Decode(encodedImage).getWidth();
                    float BitmapHeight = Decode(encodedImage).getHeight();

                    // actual coordinates of the pixel
                    float WidthRatio = BitmapWidth / ImageWidth;
                    float HeightRatio = BitmapHeight / ImageHeight;

                    float xCoordinate = (imageView.getLeft() + x) * WidthRatio ;
                    float yCoordinate = (imageView.getTop() + y ) * HeightRatio ;


                    Toast.makeText(BodyLocationActivity.this, "" + yCoordinate
                            +"," + xCoordinate, Toast.LENGTH_SHORT).show();

                    // set coordinates of the final cursor position
                    Coordinates = xCoordinate + ":" + yCoordinate ;

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

                encodedImage = Encode(image, Bitmap.CompressFormat.JPEG, 100);

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

                encodedImage = Encode(bitmap, Bitmap.CompressFormat.JPEG, 100);

                /* For encoding and decoding //

                String Base64Image = Encode(bitmap, Bitmap.CompressFormat.JPEG, 100);
                Bitmap decodedImage = Decode(Base64Image);
                imageView_BL.setImageBitmap(decodedImage);

                */

                // set the image
                imageView_BL.setImageBitmap(bitmap);
                imageView_BL.setVisibility(View.VISIBLE);

                // make the cursor visible
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

    public void selectOldBodyPhoto(View view){

    }


    public void SaveButtonClick(View view){

        EditText labelButton = findViewById(R.id.body_label);
        String label = labelButton.getText().toString();

        // Save the Photo and the point location in the File
        AddRecordActivity.bodyLocations.add(new BodyLocation(encodedImage,Coordinates,label));

        // Back to the Add Record Screen where User can Add more Photos
        finish();
    }
}
