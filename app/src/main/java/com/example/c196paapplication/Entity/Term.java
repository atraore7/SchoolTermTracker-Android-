package com.example.c196paapplication.Entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.c196paapplication.DAO.TermDAO;
import com.example.c196paapplication.Database.Repository;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "Terms")
public class Term implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int termId;

    private String termName;
    private String termStartDate;
    private String termEndDate;


    //Constructor
    public Term(int termId, String termName, String termStartDate, String termEndDate) {
        this.termId = termId;
        this.termName = termName;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }



    @Override
    public String toString() {
        return termName;
    }
}
