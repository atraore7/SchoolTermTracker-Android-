package com.example.c196paapplication.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Courses")
public class Course {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Course_ID")
    private int courseId;
    @ColumnInfo(name = "Title")
    private String courseTitle;
    @ColumnInfo(name = "Start_Date")
    private String courseStartDate;
    @ColumnInfo(name = "End_Date")
    private String courseEndDate;
    @ColumnInfo(name = "Status")
    private String courseStatus;
    @ColumnInfo(name = "Instructor_Name")
    private String courseInstructorName;
    @ColumnInfo(name = "Instructor_Phone")
    private String courseInstructorPhone;
    @ColumnInfo(name = "Instructor_Email")
    private String courseInstructorEmail;
    @ColumnInfo(name = "Term_ID")
    private int courseTermId;

    private String courseNotes;

    /*public Course(int courseId, String courseTitle, String courseStartDate, String courseEndDate, String courseStatus, String courseInstructorName, String courseInstructorPhone, String courseInstructorEmail, int courseTermId) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.courseInstructorName = courseInstructorName;
        this.courseInstructorPhone = courseInstructorPhone;
        this.courseInstructorEmail = courseInstructorEmail;
        this.courseTermId = courseTermId;
    }

     */
    public Course(int courseId, String courseTitle, String courseStartDate, String courseEndDate, String courseStatus, String courseInstructorName, String courseInstructorPhone, String courseInstructorEmail, int courseTermId, String courseNotes){
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.courseInstructorName = courseInstructorName;
        this.courseInstructorPhone = courseInstructorPhone;
        this.courseInstructorEmail = courseInstructorEmail;
        this.courseTermId = courseTermId;
        this.courseNotes = courseNotes;
    }



    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }


    public String getCourseInstructorName() {
        return courseInstructorName;
    }

    public void setCourseInstructorName(String courseInstructorName) {
        this.courseInstructorName = courseInstructorName;
    }

    public String getCourseInstructorPhone() {
        return courseInstructorPhone;
    }

    public void setCourseInstructorPhone(String courseInstructorPhone) {
        this.courseInstructorPhone = courseInstructorPhone;
    }

    public String getCourseInstructorEmail() {
        return courseInstructorEmail;
    }

    public void setCourseInstructorEmail(String courseInstructorEmail) {
        this.courseInstructorEmail = courseInstructorEmail;
    }

    public int getCourseTermId() {
        return courseTermId;
    }

    public void setTermId(int courseTermId) {
        this.courseTermId = courseTermId;
    }

    public String getCourseNotes() {
        return courseNotes;
    }

    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }

    /*@Override
    public String toString() {
        return courseTitle;
    }*/

    @Override
    public String toString() {
        return courseTitle + " " + courseId;
    }
}
