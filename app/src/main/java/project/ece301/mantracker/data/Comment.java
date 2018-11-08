package project.ece301.mantracker.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

import project.ece301.mantracker.dataConverters.DateConverter;


@Entity(tableName = "comments", indices = {@Index("id")},
        foreignKeys = {
        @ForeignKey(entity = MedicalProblem.class,
                parentColumns = "id",
                childColumns = "problem_id")})
@TypeConverters(DateConverter.class)
public class Comment {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "date")
    private Date date;

    @NonNull
    @ColumnInfo(name = "comment")
    private String description;

    public Comment(@NonNull Date date, @NonNull String description) {
        this.date = date;
        this.description = description;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }
}
