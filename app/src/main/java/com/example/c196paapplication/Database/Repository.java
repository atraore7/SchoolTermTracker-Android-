package com.example.c196paapplication.Database;

import android.app.Application;

import com.example.c196paapplication.DAO.AssessmentDAO;
import com.example.c196paapplication.DAO.CourseDAO;
import com.example.c196paapplication.DAO.TermDAO;
import com.example.c196paapplication.Entity.Assessment;
import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.Entity.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    public TermDAO nTermDAO;
    public CourseDAO nCourseDAO;
    public AssessmentDAO nAssessmentDAO;
    List<Assessment> nAllAssessments;
    List<Term> nAllTerms;
    List<Course> nAllCourses;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        thisDatabaseBuilder database = thisDatabaseBuilder.getDatabase(application);
        nTermDAO = database.termDAO();
        nCourseDAO = database.courseDAO();
        nAssessmentDAO = database.assessmentDAO();
    }

    //Term INSERT, UPDATE, DELETE, getAllTerms
    public void insert(Term term){
        databaseExecutor.execute(()->{
            nTermDAO.Insert(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }
    public void update(Term term){
        databaseExecutor.execute(()->{
            nTermDAO.Update(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }
    public void delete(Term term){
        databaseExecutor.execute(()->{
            nTermDAO.delete(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }
    public List<Term> getAllTerms(){
        databaseExecutor.execute(()->{
            nAllTerms =nTermDAO.getAllTerms();
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        return nAllTerms;
    }

    //Course INSERT, UPDATE, DELETE, getAllCourses
    public List<Course> getAllCourses(){
        databaseExecutor.execute(()->{
            nAllCourses = nCourseDAO.getAllCourses();
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        return nAllCourses;
    }
    public void insert(Course course){
        databaseExecutor.execute(()->{
            nCourseDAO.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Course course){
        databaseExecutor.execute(()->{
            nCourseDAO.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Course course){
        databaseExecutor.execute(()->{
            nCourseDAO.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Assessment INSERT, UPDATE, DELETE, getAllAssessments
    public void insert(Assessment assessment){
        databaseExecutor.execute(()->{
            nAssessmentDAO.insert(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }
    public void update(Assessment assessment){
        databaseExecutor.execute(()->{
            nAssessmentDAO.update(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }
    public void delete(Assessment assessment){
        databaseExecutor.execute(()->{
            nAssessmentDAO.delete(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }
    public List<Assessment> getAllAssessments(){
        databaseExecutor.execute(()->{
            nAllAssessments =nAssessmentDAO.getAllAssessments();
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        return nAllAssessments;
    }

}
