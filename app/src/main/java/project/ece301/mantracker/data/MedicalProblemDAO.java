package project.ece301.mantracker.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.TypeConverters;

import project.ece301.mantracker.dataConverters.DateConverter;

@Dao
@TypeConverters(DateConverter.class)
public interface MedicalProblemDAO {
}
