package com.example.c196paapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.c196paapplication.DAO.AssessmentDAO;
import com.example.c196paapplication.Database.Repository;
import com.example.c196paapplication.Entity.Assessment;
import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.Entity.Term;
import com.example.c196paapplication.R;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(getApplication());

        Term term = new Term(1, "Term 1", "08/12/22", "10/30/22");
        Term term2 = new Term(2, "Term 2", "09/16/22", "12/30/22");
        repository.insert(term);
        repository.insert(term2);


        Course course = new Course(1, "Algebra", "09/12/22", "12/19/22", "In Progress", "June Knight", "123-123-1234", "email@email.com", 1, "");
        Course course1 = new Course(2, "Biology", "12/12/22", "01/12/22", "In Progress", "Betty", "123-123-1234", "email", 2, "");
        repository.insert(course);
        repository.insert(course1);

        Assessment assessment = new Assessment(1, "Test", "09/24/22", "Objective", 1);
        repository.insert(assessment);


    }

    public void onTermsClick(View view) {
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);

    }

    public void onCoursesClick(View view) {
        Intent intent = new Intent(MainActivity.this, CourseList.class);
        startActivity(intent);
    }

    public void onAssessmentsClick(View view){
        Intent intent = new Intent(MainActivity.this, AssessmentList.class);
        startActivity(intent);
    }

   /* //Menu
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
                Intent intent = new Intent(MainActivity.this, TermList.class);
                startActivity(intent);
                return true;
            case R.id.course:
                Intent intent1 = new Intent(MainActivity.this, CourseList.class);
                startActivity(intent1);
                return true;
            case R.id.assessments:
                Intent intent2 = new Intent(MainActivity.this, AssessmentList.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    */


}