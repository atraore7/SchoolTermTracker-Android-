package com.example.c196paapplication.UI;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.c196paapplication.Database.Repository;
import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.Entity.Term;
import com.example.c196paapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TermDetails extends AppCompatActivity {

    EditText termNameInput;
    EditText termStartInput;
    EditText termEndInput;
    String name;
    String start;
    String end;
    Repository repository;
    int termId;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        //
        termNameInput = findViewById(R.id.termNameInput);
        termStartInput = findViewById(R.id.startInput);
        termEndInput = findViewById(R.id.endInput);
        //to populate field with data from term selected (set up in termAdapter onclick
        termId = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        //setting text field with data.
        termNameInput.setText(name);
        termStartInput.setText(start);
        termEndInput.setText(end);
        repository = new Repository(getApplication());

        //date formatting and datePickers
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

        termStartInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String startInfo = termStartInput.getText().toString();
                //if(startInfo.equals(""))startInfo="10/11/22";
                try{
                    myCalendarStart.setTime(sdf.parse(startInfo));
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetails.this, startDate, myCalendarStart
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

        termEndInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String endInfo = termEndInput.getText().toString();
                //if(startInfo.equals(""))startInfo="10/11/22";
                try{
                    myCalendarStart.setTime(sdf.parse(endInfo));
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetails.this, endDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




    }
    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        termStartInput.setText(sdf.format(myCalendarStart.getTime()));
    }
    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        termEndInput.setText(sdf.format(myCalendarStart.getTime()));
    }



    public void onSave(View view) {
        Term term;
        if(termId == -1){
            int newId = repository.getAllTerms().get(repository.getAllTerms().size() -1).getTermId() + 1;
            term = new Term(newId, termNameInput.getText().toString(), termStartInput.getText().toString(), termEndInput.getText().toString());
            repository.insert(term);
        }
        else{
            term = new Term(termId, termNameInput.getText().toString(), termStartInput.getText().toString(), termEndInput.getText().toString());
            repository.update(term);
        }
        //Direct back to TermList screen after save.
        Intent intent = new Intent(TermDetails.this, TermList.class);
        startActivity(intent);


    }

    public void onDelete(View view) {
        ArrayList<Course> list = new ArrayList<>();
        for(Course course :repository.getAllCourses()){
            if(course.getCourseTermId() == termId){
                list.add(course);
            }
        }
        if(list.isEmpty()) {
            for (Term term : repository.getAllTerms()) {
                if (term.getTermId() == termId) {
                    repository.delete(term);
                    Toast.makeText(TermDetails.this, "Term was deleted", Toast.LENGTH_LONG).show();
                    //direct back to termList screen after successful delete
                    Intent intent = new Intent(TermDetails.this, TermList.class);
                    startActivity(intent);
                }
            }
        }
        else{
            Toast.makeText(TermDetails.this,  "Term cannot be deleted because this term has courses assigned to it.", Toast.LENGTH_LONG).show();
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notify_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            //Notify
            case R.id.notifyStart:
                String date=termStartInput.getText().toString();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date startDate=null;
                try {
                    startDate=sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger=startDate.getTime();
                Intent intent= new Intent(TermDetails.this,MyReceiver.class);
                intent.putExtra("key",termNameInput.getText().toString() + " starts today.");
                PendingIntent sender=PendingIntent.getBroadcast(TermDetails.this, MainActivity.numAlert++,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.notifyEnd:
                String newDate=termEndInput.getText().toString();
                String format = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
                Date endDate=null;
                try {
                    endDate=simpleDateFormat.parse(newDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long endTrigger=endDate.getTime();
                Intent newIntent= new Intent(TermDetails.this,MyReceiver.class);
                newIntent.putExtra("key",termNameInput.getText().toString() + " ends today.");
                PendingIntent newSender=PendingIntent.getBroadcast(TermDetails.this, MainActivity.numAlert++,newIntent,0);
                AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, endTrigger, newSender);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }



}