package project.ece301.mantracker.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

import project.ece301.mantracker.dataConverters.DateConverter;


@Entity(tableName = "comments", foreignKeys = {
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
}
