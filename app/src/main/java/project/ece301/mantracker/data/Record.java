package project.ece301.mantracker.data;
// see https://codelabs.developers.google.com/codelabs/android-persistence/#0
// see https://github.com/googlecodelabs/android-persistence/tree/master/app/src
// /main/java/com/example/android/persistence/codelab/db

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

import project.ece301.mantracker.util.Geolocation;

@Entity(tableName = "records", foreignKeys = {
        @ForeignKey(entity = MedicalProblem.class,
                parentColumns = "id",
                childColumns = "problem_id")})
@TypeConverters(DateConverter.class)
public class Record {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

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

    @ColumnInfo(name = "problem_id")
    private BodyLocation problemId;

    public Record(@NonNull Date date, @NonNull String title, @NonNull String description,
                  Geolocation geolocation, BodyLocation bodyLocation) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.geolocation = geolocation;
        this.bodyLocation = bodyLocation;
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
