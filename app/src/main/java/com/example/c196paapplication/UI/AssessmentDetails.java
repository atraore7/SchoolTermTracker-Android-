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
import com.example.c196paapplication.Entity.Assessment;
import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.R;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AssessmentDetails extends AppCompatActivity {

    Repository repository;
    int assessmentId;
    String name;
    String end;
    String type;
    int courseId;
    Spinner courseSpinner;
    Spinner typeSpinner;
    EditText nameInput;
    EditText endInput;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        repository = new Repository(getApplication());

        //getting assessment info from adapter and setting to a String variable
        assessmentId = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        end = getIntent().getStringExtra("endDate");
        type = getIntent().getStringExtra("type");
        courseId = getIntent().getIntExtra("courseId", -1);

        //setting edittext variable to the edittext in the xml
        nameInput = findViewById(R.id.nameInput0);
        endInput = findViewById(R.id.endInput0);

        //setting editText value to the passes in object
        nameInput.setText(name);
        endInput.setText(end);

        //populating the type spinner
        typeSpinner = (Spinner) findViewById(R.id.assessmentTypeSpinner);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.CourseTypes, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        //if type is not null set spinner value to the correct type.
        if(type != null){
            int spinnerPosition = typeAdapter.getPosition(type);
            typeSpinner.setSelection(spinnerPosition);
        }

        //setting items in courseSpinner
        courseSpinner = (Spinner) findViewById(R.id.courseSpinner2);
        ArrayAdapter<Course> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, repository.getAllCourses());
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);
        //sets the course spinner to corresponding course
        for(Course course : repository.getAllCourses()){
            if(course.getCourseId() == courseId){
                int termSpinnerPosition = courseAdapter.getPosition(course);
                courseSpinner.setSelection(termSpinnerPosition);
            }
        }

        //Setting Date Picker For End Date
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelEnd();
            }

        };

        endInput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info=endInput.getText().toString();
                if(info.equals(""))info="02/10/22";
                try{
                    myCalendar.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetails.this, endDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        endInput.setText(sdf.format(myCalendar.getTime()));
    }
    public void onCourseSave(View view) {
        Assessment assessment;
        if(assessmentId == -1){
            int newId = repository.getAllAssessments().get(repository.getAllAssessments().size() -1).getAssessmentId() + 1;
            assessment = new Assessment(newId, nameInput.getText().toString(), endInput.getText().toString(), type, courseId);
            repository.insert(assessment);
        }
        else{
            assessment = new Assessment(assessmentId, nameInput.getText().toString(), endInput.getText().toString(), type, courseId);
            repository.update(assessment);
        }
        Intent intent = new Intent(AssessmentDetails.this, AssessmentList.class);
        startActivity(intent);
    }

    public void onDelete(View view) {
        for(Assessment assessment : repository.getAllAssessments()){
            if(assessment.getAssessmentId() == assessmentId){
                repository.delete(assessment);

                Intent intent = new Intent(AssessmentDetails.this, AssessmentList.class);
                startActivity(intent);
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.end_notify_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.notifyEnd:
                String dateFromScreen=endInput.getText().toString();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate=null;
                try {
                    myDate=sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger=myDate.getTime();
                Intent intent= new Intent(AssessmentDetails.this,MyReceiver.class);
                intent.putExtra("key",nameInput.getText().toString() + " ends today.");
                PendingIntent sender=PendingIntent.getBroadcast(AssessmentDetails.this, ++MainActivity.numAlert,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}