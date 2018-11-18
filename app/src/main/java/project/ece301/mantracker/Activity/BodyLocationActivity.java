package project.ece301.mantracker.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import project.ece301.mantracker.R;

import static project.ece301.mantracker.MedicalProblem.UploadPhoto.UploadFromCamera;
import static project.ece301.mantracker.MedicalProblem.UploadPhoto.UploadFromGallery;

public class BodyLocationActivity extends AppCompatActivity {

    // TODO : DELETE IT // PLACE HOLDER VARIABLES
    String Coordinates;

    private static int REQUEST_CODE = 0;

    // Gallery Permissions
    private static String[] GALLERY_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // CAMERA Permissions
    private static String[] CAMERA_PERMISSIONS = {
            Manifest.permission.CAMERA
    };

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
        UploadFromCamera(this);
    }

    public void GalleryPhoto(View view){
        UploadFromGallery(this);
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

        if (requestCode == 2 && resultCode == RESULT_OK && null != data) {

            Toast.makeText(this, "In Camera.", Toast.LENGTH_SHORT).show();

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

        else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

            Toast.makeText(this, "In Gallery.", Toast.LENGTH_SHORT).show();

            Uri imageSelected = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };

            try{
                Cursor cursor = getContentResolver().query(imageSelected ,
                        filePath, null, null, null);
                cursor.moveToFirst();

                int column_no = cursor.getColumnIndex(filePath[0]);
                String picturePath = cursor.getString(column_no);
                cursor.close();

                imageView_BL.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                imageView_BL.setVisibility(View.VISIBLE);

                // make the cursor visible
                CursorImage.setVisibility(View.VISIBLE);


//                Bitmap bitmap = ((BitmapDrawable) imagetView_BL.getDrawable()).getBitmap();
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                byte[] imageInByte = baos.toByteArray();
//
//                String image_BL = imageInByte.toString();
//                Toast.makeText(this, image_BL, Toast.LENGTH_SHORT).show();
//
//                byte[] bytes = image_BL.getBytes();

            }
            catch (NullPointerException ex){
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
