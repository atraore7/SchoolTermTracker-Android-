package com.example.c196paapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.Entity.Term;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM Courses ORDER BY Course_ID ASC")
    List<Course> getAllCourses();

}
