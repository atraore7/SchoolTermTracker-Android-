package com.example.c196paapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.c196paapplication.Database.Repository;
import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.Entity.Term;
import com.example.c196paapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CourseDetails extends AppCompatActivity {

    int courseId;
    int termId;
    String title;
    String start;
    String end;
    String status;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    String notes;
    EditText courseTitle;
    EditText courseStart;
    EditText courseEnd;
    EditText courseInstructor;
    EditText coursePhone;
    EditText courseEmail;
    EditText courseNotes;
    Spinner termSpinner;
    Spinner statusSpinner;
    Repository repository;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        repository = new Repository(getApplication());
        //getting course info out of adapter and assigning to a variable.
        courseId = getIntent().getIntExtra("id", -1);
        //getting title from CourseAdapter
        title = getIntent().getStringExtra("courseTitle");
        //setting EditText variable courseTitle to the specific EditText courseTitleInput that is in the xml
        courseTitle = findViewById(R.id.titleInput6);
        //setting courseTitleInput field to the matching title of the course that is passed through the adapter
        courseTitle.setText(title);
        start = getIntent().getStringExtra("start");
        courseStart = findViewById(R.id.startInput6);
        courseStart.setText(start);
        end = getIntent().getStringExtra("end");
        courseEnd = findViewById(R.id.endInput6);
        courseEnd.setText(end);
        instructorName = getIntent().getStringExtra("instructor");
        courseInstructor = findViewById(R.id.instructorInput6);
        courseInstructor.setText(instructorName);
        instructorPhone = getIntent().getStringExtra("phone");
        coursePhone = findViewById(R.id.phoneInput6);
        coursePhone.setText(instructorPhone);
        instructorEmail = getIntent().getStringExtra("email");
        courseEmail = findViewById(R.id.emailInput6);
        courseEmail.setText(instructorEmail);
        courseNotes = findViewById(R.id.MultiLineNotes6);
        notes = getIntent().getStringExtra("notes");
        courseNotes.setText(notes);


        //selectSpinnerByValue(termSpinner, Long.valueOf(termId));
        status = getIntent().getStringExtra("status");
        statusSpinner = (Spinner)  findViewById(R.id.statusSpinner6);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.Statuses, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);
        if(status != null){
            int spinnerPosition = statusAdapter.getPosition(status);
            statusSpinner.setSelection(spinnerPosition);
        }

        termId = getIntent().getIntExtra("termId", -1);
        termSpinner = (Spinner) findViewById(R.id.termSpinner6);
        ArrayAdapter<Term> termAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, repository.getAllTerms());
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);
        for(Term term : repository.getAllTerms()){
            if(term.getTermId() == termId){
                int termSpinnerPosition = termAdapter.getPosition(term);
                termSpinner.setSelection(termSpinnerPosition);
            }
        }

        //Start Date Picker
        String format = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, month);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelStart();
            }
        };

        courseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String startInfo = courseStart.getText().toString();
                //if(startInfo.equals(""))startInfo="10/11/22";
                try{
                    myCalendarStart.setTime(sdf.parse(startInfo));
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //End DatePicker
        endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, month);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelEnd();
            }
        };

        courseEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String endInfo = courseEnd.getText().toString();
                //if(startInfo.equals(""))startInfo="10/11/22";
                try{
                    myCalendarStart.setTime(sdf.parse(endInfo));
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, endDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




    }
    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        courseStart.setText(sdf.format(myCalendarStart.getTime()));
    }
    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        courseEnd.setText(sdf.format(myCalendarStart.getTime()));
    }

    public void onCourseSave(View view) {
        Course course;
        if(courseId == -1){
            int newId = repository.getAllCourses().get(repository.getAllCourses().size() -1).getCourseId() + 1;
            course = new Course(newId, courseTitle.getText().toString(), courseStart.getText().toString(), courseEnd.getText().toString(), status, courseInstructor.getText().toString(), coursePhone.getText().toString(), courseEmail.getText().toString(), termId, courseNotes.getText().toString());
            repository.insert(course);
        }
        else{
            course = new Course(courseId, courseTitle.getText().toString(), courseStart.getText().toString(), courseEnd.getText().toString(), status, courseInstructor.getText().toString(), coursePhone.getText().toString(), courseEmail.getText().toString(), termId, courseNotes.getText().toString());
            repository.update(course);
        }
        //Direct back to TermList screen after save.
        Intent intent = new Intent(CourseDetails.this, CourseList.class);
        startActivity(intent);
    }

    public void onCourseDelete(View view) {
        for(Course course : repository.getAllCourses()){
            if(course.getCourseId() == courseId){
                repository.delete(course);

                //direct back to termList screen after successful delete
                Intent intent = new Intent(CourseDetails.this, CourseList.class);
                startActivity(intent);
            }
        }

    }
    //Menu
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
                sendIntent.putExtra(Intent.EXTRA_TEXT, courseNotes.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, courseTitle.getText().toString() + " notes");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            //Notifications
            case R.id.startNotify:
                String date = courseStart.getText().toString();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date sd=null;
                try {
                    sd=sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger=sd.getTime();
                Intent intent= new Intent(CourseDetails.this,MyReceiver.class);
                intent.putExtra("key",courseTitle.getText().toString() + " starts today.");
                PendingIntent sender=PendingIntent.getBroadcast(CourseDetails.this, MainActivity.numAlert++,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.endNotify:
                String newDate=courseEnd.getText().toString();
                String format = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
                Date ed=null;
                try {
                    ed=simpleDateFormat.parse(newDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long endTrigger=ed.getTime();
                Intent newIntent= new Intent(CourseDetails.this,MyReceiver.class);
                newIntent.putExtra("key",courseTitle.getText().toString() + " ends today.");
                PendingIntent newSender=PendingIntent.getBroadcast(CourseDetails.this, MainActivity.numAlert++,newIntent,0);
                AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, endTrigger, newSender);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}