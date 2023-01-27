package com.example.c196paapplication.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196paapplication.Entity.Term;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void Insert(Term term);

    @Update
    void Update(Term term);

    @Delete
    void delete(Term term);

    @Query("SELECT * FROM Terms ORDER BY termId ASC")
    List<Term> getAllTerms();
}
