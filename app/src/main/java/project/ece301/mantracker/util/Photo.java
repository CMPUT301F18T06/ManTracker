package project.ece301.mantracker.util;

import android.graphics.Bitmap;

public interface Photo {
    void setPhoto (Bitmap photo);
    void showPhoto (Bitmap photo); // TODO: remove from classes - this is not a model task
    Bitmap getPhoto();
}
