package project.ece301.mantracker.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;


@Entity(tableName = "records", foreignKeys = {
        @ForeignKey(entity = MedicalProblem.class,
                parentColumns = "id",
                childColumns = "problem_id")})
@TypeConverters(DateConverter.class)
public class Comment {

}
