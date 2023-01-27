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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.c196paapplication.DAO.TermDAO;
import com.example.c196paapplication.Database.Repository;
import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.Entity.Term;
import com.example.c196paapplication.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CourseList extends AppCompatActivity {
    private Repository repository;
    /*private int courseTermId;
    private int courseId;
    private String courseTitle;
    private String courseStart;
    private String courseEnd;
    private String courseStatus;
    private String courseInstructorName;
    private String courseInstructorPhone;
    private String courseInstructorEmail;

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        /*courseId = getIntent().getIntExtra("id", -1);
        courseTitle = getIntent().getStringExtra("courseTitle");
        courseStart = getIntent().getStringExtra("start");
        courseEnd = getIntent().getStringExtra("end");
        courseStatus = getIntent().getStringExtra("status");
        courseInstructorName = getIntent().getStringExtra("instructor");
        courseInstructorPhone = getIntent().getStringExtra("phone");
        courseInstructorEmail = getIntent().getStringExtra("email");
        courseTermId = getIntent().getIntExtra("termId", -1);

         */
        //setting RecycleView
        repository  = new Repository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //setting spinner items
        Spinner termSpinner = (Spinner) findViewById(R.id.CourseListTermSpinner);
        ArrayList<Term> terms = new ArrayList<>();
        for(Term term : repository.getAllTerms()){
            terms.add(term);
        }
        ArrayAdapter<Term> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, terms);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(spinnerAdapter);
        //setting recycleView courses for the term that is in spinner on load.
        Term selectedTerm = (Term) termSpinner.getSelectedItem();
        ArrayList<Course> matchedCourses = new ArrayList<>();
        System.out.println(repository.getAllCourses());
        for(Course course : repository.getAllCourses()){
            if(selectedTerm.getTermId() == course.getCourseTermId()){
               matchedCourses.add(course);
            }
        }
        courseAdapter.setCourses(matchedCourses);
        System.out.println(matchedCourses);
        //setting recycleView courses for a term that is selected in spinner
        termSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Term selection = (Term) adapterView.getSelectedItem();
                ArrayList<Course> termCourses = new ArrayList<>();
                for(Course course : repository.getAllCourses()){
                    if(course.getCourseTermId() == selection.getTermId()){
                        termCourses.add(course);
                    }
                }
                courseAdapter.setCourses(termCourses);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void onAddCourse(View view) {
        Intent intent = new Intent(CourseList.this, AddCourse.class);
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
                Intent intent = new Intent(CourseList.this, TermList.class);
                startActivity(intent);
                return true;
            case R.id.course:
                Intent intent1 = new Intent(CourseList.this, CourseList.class);
                startActivity(intent1);
                return true;
            case R.id.assessments:
                Intent intent2 = new Intent(CourseList.this, AssessmentList.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}