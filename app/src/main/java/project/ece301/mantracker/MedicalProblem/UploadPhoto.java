package project.ece301.mantracker.MedicalProblem;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.widget.Button;

import java.io.ByteArrayOutputStream;

import project.ece301.mantracker.R;

import static android.support.v4.app.ActivityCompat.requestPermissions;

public class UploadPhoto {

    private static int REQUEST_CODE = 0;

    // Gallery Permissions
    private static String[] GALLERY_PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    // CAMERA Permissions
    private static String[] CAMERA_PERMISSIONS = {
            Manifest.permission.CAMERA
    };

    public static void UploadFromCamera(Activity activity){

        REQUEST_CODE = 1;

        Intent cameraPhoto = new Intent(android.provider.
                MediaStore.ACTION_IMAGE_CAPTURE);

        activity.startActivityForResult(cameraPhoto, REQUEST_CODE);
    }

    public static void UploadFromGallery(Activity activity){

        REQUEST_CODE = 2;

        Intent uploadPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        activity.startActivityForResult(uploadPhoto, REQUEST_CODE);
    }


    // Check if user has the right permissions
    public static void CheckPermissionsGallery(Activity activity) {

        REQUEST_CODE = 2;
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // if we don't have permissions ten ask from the user
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(activity, GALLERY_PERMISSIONS, REQUEST_CODE);
        }else{
            UploadFromGallery(activity);
        }
    }

    // Check if user has the right permissions
    public static void CheckPermissionsCamera(Activity activity){

        REQUEST_CODE = 1;
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        // if we don't have permissions then ask from the user
        if ( permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(activity, CAMERA_PERMISSIONS, REQUEST_CODE);
        }else{
            UploadFromCamera(activity);
        }
    }


    // credit Roman Truba from Stack Overflow
    // https://stackoverflow.com/questions/9768611/encode-and-decode-bitmap-object-in-base64-string-in-android

    public static String Encode(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, byteArray);
        return Base64.encodeToString(byteArray.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap Decode(String Base64Image)
    {
        byte[] decodedBytes = Base64.decode(Base64Image, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
