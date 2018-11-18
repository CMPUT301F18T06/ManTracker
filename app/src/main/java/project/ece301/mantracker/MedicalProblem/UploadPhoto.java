package project.ece301.mantracker.MedicalProblem;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class UploadPhoto {

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

    public static void UploadFromCamera(Activity activity){

        REQUEST_CODE = 2;

        CheckPermissionsCamera(activity);

        Intent cameraPhoto = new Intent(android.provider.
                MediaStore.ACTION_IMAGE_CAPTURE);

        activity.startActivityForResult(cameraPhoto, REQUEST_CODE);
    }

    public static void UploadFromGallery(Activity activity){

        REQUEST_CODE = 1;

        CheckPermissionsGallery(activity);

        Intent uploadPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        activity.startActivityForResult(uploadPhoto, REQUEST_CODE);
    }

    /*
    Check if you have the right permissions
    */
    private static void CheckPermissionsGallery(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // if we don't have permissions ten ask from the user
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    GALLERY_PERMISSIONS, REQUEST_CODE);
        }
    }

    private static void CheckPermissionsCamera(Activity activity){
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA);

        // if we don't have permissions ten ask from the user
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    CAMERA_PERMISSIONS, REQUEST_CODE);
        }
    }
}
