package project.ece301.mantracker.MedicalProblem;

import android.graphics.Bitmap;

public interface Photo {
    void setPhoto (Bitmap photo);
    void showPhoto (Bitmap photo);
    Bitmap getPhoto();
}
