package com.example.c196paapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.c196paapplication.Database.Repository;
import com.example.c196paapplication.Entity.Assessment;
import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.R;

import java.util.ArrayList;

public class AssessmentList extends AppCompatActivity {

    /*private int assessmentId;
    private String assessmentName;
    private String assessmentEndDate;
    private String assessmentType;
    private int assessmentCourseId;

     */
    Repository repository;
    //Spinner courseSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        repository = new Repository(getApplication());
        /*
        assessmentId = getIntent().getIntExtra("id", -1);
        assessmentName = getIntent().getStringExtra("name");
        assessmentEndDate = getIntent().getStringExtra("endDate");
        assessmentType = getIntent().getStringExtra("type");
        assessmentCourseId = getIntent().getIntExtra("courseId", -1);

         */
        //setting RecycleView
        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //setting spinner items
        Spinner courseSpinner = (Spinner) findViewById(R.id.courseSpinner);
        ArrayList<Course> courses = new ArrayList<>();
        for(Course course : repository.getAllCourses()){
            courses.add(course);
        }
        ArrayAdapter<Course> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(spinnerAdapter);

        //setting recycleView courses for the term that is in spinner on load.
        Course selectedCourse = (Course) courseSpinner.getSelectedItem();
        ArrayList<Assessment> matchedAssessments = new ArrayList<>();
        for(Assessment assessment : repository.getAllAssessments()){
            if(selectedCourse.getCourseId() == assessment.getAssessmentCourseId()){
                matchedAssessments.add(assessment);
            }
        }
        assessmentAdapter.setAssessments(matchedAssessments);
        //setting recycleView courses for a term that is selected in spinner
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Course selection = (Course) adapterView.getSelectedItem();
                ArrayList<Assessment> courseAssessments = new ArrayList<>();
                for(Assessment assessment : repository.getAllAssessments()){
                    if(selection.getCourseId() == assessment.getAssessmentCourseId()){
                        courseAssessments.add(assessment);
                    }
                }
                assessmentAdapter.setAssessments(courseAssessments);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    public void onAddAssessment(View view) {
        Intent intent = new Intent(AssessmentList.this, AddAssessment.class);
        startActivity(intent);
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_termlist, menu);
        return true;
    }
    //on Menu selection
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.term:
                Intent intent = new Intent(AssessmentList.this, TermList.class);
                startActivity(intent);
                return true;
            case R.id.course:
                Intent intent1 = new Intent(AssessmentList.this, CourseList.class);
                startActivity(intent1);
                return true;
            case R.id.assessments:
                Intent intent2 = new Intent(AssessmentList.this, AssessmentList.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}