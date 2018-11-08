package project.ece301.mantracker.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "problems", foreignKeys = {
        @ForeignKey(entity = Account.class,
                parentColumns = "id",
                childColumns = "user_id")})
@TypeConverters(DateConverter.class)
public class MedicalProblem {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "user_id")
    private String username;

    @NonNull
    @ColumnInfo(name = "date")
    private Date date;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    public MedicalProblem(@NonNull String username, @NonNull Date date,
                          @NonNull String title, String description) {
        this.username = username;
        this.date = date;
        this.title = title;
        this.description = description;
    }
}
