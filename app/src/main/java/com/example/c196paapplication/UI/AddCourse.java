package com.example.c196paapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.c196paapplication.Database.Repository;
import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.Entity.Term;
import com.example.c196paapplication.R;

public class AddCourse extends AppCompatActivity {
    Repository repository;
    EditText titleInput5;
    EditText startInput5;
    EditText endInput5;
    EditText instructorInput5;
    EditText phoneInput5;
    EditText emailInput5;
    EditText notes;
    Spinner termSpinner3;
    Spinner statusSpinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        repository = new Repository(getApplication());

        titleInput5 = findViewById(R.id.titleInput6);
        startInput5 = findViewById(R.id.startInput6);
        endInput5 = findViewById(R.id.endInput6);
        instructorInput5 = findViewById(R.id.instructorInput6);
        phoneInput5 = findViewById(R.id.phoneInput6);
        emailInput5 = findViewById(R.id.emailInput6);
        notes = findViewById(R.id.MultiLineNotes6);

        //setting termSpinner items
        termSpinner3 = (Spinner) findViewById(R.id.termSpinner6);
        ArrayAdapter<Term> termAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, repository.getAllTerms());
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner3.setAdapter(termAdapter);

        //setting statusSpinner items
        statusSpinner3 = (Spinner) findViewById(R.id.statusSpinner6);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.Statuses, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner3.setAdapter(statusAdapter);


    }

    public void onCourseSave(View view) {
        int newId = repository.getAllCourses().get(repository.getAllCourses().size() -1).getCourseId() + 1;
        Term term = (Term) termSpinner3.getSelectedItem();
        int termId = term.getTermId();


            Course course = new Course(newId, titleInput5.getText().toString(), startInput5.getText().toString(), endInput5.getText().toString(), statusSpinner3.getSelectedItem().toString(), instructorInput5.getText().toString(), phoneInput5.getText().toString(), emailInput5.getText().toString(), termId, notes.getText().toString());
            repository.insert(course);



        Intent intent = new Intent(AddCourse.this, CourseList.class);
        startActivity(intent);
    }


    public void onCourseCancel(View view) {
        Intent intent = new Intent(AddCourse.this, CourseList.class);
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            //Sharing
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, notes.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, titleInput5.getText().toString() + " Notes");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

        }
        return super.onOptionsItemSelected(item);
    }

}