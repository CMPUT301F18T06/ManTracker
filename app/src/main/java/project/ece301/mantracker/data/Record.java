package project.ece301.mantracker.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "records")
public class Record {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "date")
    private Date date;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "geolocation")
    private Geolocation geolocation;

    @ColumnInfo(name = "body_location")
    private BodyLocation bodyLocation;

    public Record(@NonNull Date date, @NonNull String title, @NonNull String description,
                  Geolocation geolocation, BodyLocation bodyLocation) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.geolocation = geolocation;
        this.bodyLocation = bodyLocation;
    }

    public Record(){

    }

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public BodyLocation getBodyLocation() {
        return bodyLocation;
    }
}
