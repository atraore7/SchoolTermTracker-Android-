package com.example.c196paapplication.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196paapplication.DAO.AssessmentDAO;
import com.example.c196paapplication.DAO.CourseDAO;
import com.example.c196paapplication.DAO.TermDAO;
import com.example.c196paapplication.Entity.Assessment;
import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.Entity.Term;

@Database(entities = {Term.class, Assessment.class, Course.class}, version = 16, exportSchema = false)
public abstract class thisDatabaseBuilder extends RoomDatabase {

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();

    private static volatile thisDatabaseBuilder INSTANCE;

    public static thisDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Builder.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), thisDatabaseBuilder.class, "MyDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
