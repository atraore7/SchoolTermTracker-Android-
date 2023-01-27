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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddAssessment extends AppCompatActivity {

    Repository repository;
    EditText nameInput;
    EditText endInput;
    Spinner typeSpinner;
    Spinner courseSpinner;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);

        repository = new Repository(getApplication());

        nameInput = findViewById(R.id.nameInput0);
        endInput = findViewById(R.id.endInput0);

        //setting courseSpinner items
        courseSpinner = (Spinner) findViewById(R.id.courseSpinner2);
        ArrayAdapter<Course> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, repository.getAllCourses());
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);

        //setting typeSpinner items
        typeSpinner = (Spinner) findViewById(R.id.assessmentTypeSpinner);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.CourseTypes, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        //Date Picker for end date
        String myFormat = "MM/dd/yy";
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
                new DatePickerDialog(AddAssessment.this, endDate, myCalendar
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

    public void onAssessmentSave(View view) {
        int newId = repository.getAllAssessments().get(repository.getAllAssessments().size() -1).getAssessmentId() + 1;
        Course course = (Course) courseSpinner.getSelectedItem();
        int courseId = course.getCourseId();

        Assessment assessment = new Assessment(newId, nameInput.getText().toString(), endInput.getText().toString(), typeSpinner.getSelectedItem().toString(), courseId);
        repository.insert(assessment);

        Intent intent = new Intent(AddAssessment.this, AssessmentList.class);
        startActivity(intent);
    }

    public void onCancel(View view) {
        Intent intent = new Intent(AddAssessment.this, AssessmentList.class);
        startActivity(intent);
    }

    //Menu
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
                Intent intent= new Intent(AddAssessment.this,MyReceiver.class);
                intent.putExtra("key",nameInput.getText().toString() + " ends today.");
                PendingIntent sender=PendingIntent.getBroadcast(AddAssessment.this, ++MainActivity.numAlert,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}