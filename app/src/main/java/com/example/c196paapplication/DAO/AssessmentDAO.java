package com.example.c196paapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196paapplication.Entity.Assessment;
import com.example.c196paapplication.Entity.Course;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM Assessments ORDER BY assessmentId ASC")
    List<Assessment> getAllAssessments();

}
