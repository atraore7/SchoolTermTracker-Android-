package com.example.c196paapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Assessments")
public class Assessment {

    @PrimaryKey(autoGenerate = true)

    private int assessmentId;
    private String assessmentName;
    private String assessmentEndDate;
    private String assessmentType;
    private int assessmentCourseId;

    public Assessment(int assessmentId, String assessmentName, String assessmentEndDate, String assessmentType, int assessmentCourseId) {
        this.assessmentId = assessmentId;
        this.assessmentName = assessmentName;
        this.assessmentEndDate = assessmentEndDate;
        this.assessmentType = assessmentType;
        this.assessmentCourseId = assessmentCourseId;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentEndDate() {
        return assessmentEndDate;
    }

    public void setAssessmentEndDate(String assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }
    public String  getAssessmentType() {
        return assessmentType;
    }
    public void setAssessmentType(String assessmentType){
        this.assessmentType = assessmentType;
    }
    public int getAssessmentCourseId(){
        return assessmentCourseId;
    }

    @Override
    public String toString() {
        return assessmentName + " " + assessmentType;
    }
}
