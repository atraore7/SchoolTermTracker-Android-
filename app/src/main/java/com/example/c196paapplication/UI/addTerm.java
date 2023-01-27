package com.example.c196paapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.c196paapplication.Database.Repository;
import com.example.c196paapplication.Entity.Term;
import com.example.c196paapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class addTerm extends AppCompatActivity {
    Repository repository;
    EditText termNameInput;
    EditText termStartInput;
    EditText termEndInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addterm);

        termNameInput = findViewById(R.id.termNameInput);
        termStartInput = findViewById(R.id.startInput);
        termEndInput = findViewById(R.id.endInput);
        repository = new Repository(getApplication());


    }

    public void onSave(View view) {
        int newId = repository.getAllTerms().get(repository.getAllTerms().size() -1).getTermId() + 1;
        Term term = new Term(newId, termNameInput.getText().toString(), termStartInput.getText().toString(), termEndInput.getText().toString());
        repository.insert(term);

        Intent intent = new Intent(addTerm.this, TermList.class);
        startActivity(intent);


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
                Intent intent= new Intent(addTerm.this,MyReceiver.class);
                intent.putExtra("key",termNameInput.getText().toString() + " starts today.");
                PendingIntent sender=PendingIntent.getBroadcast(addTerm.this, MainActivity.numAlert++,intent,0);
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
                Intent newIntent= new Intent(addTerm.this,MyReceiver.class);
                newIntent.putExtra("key",termNameInput.getText().toString() + " ends today.");
                PendingIntent newSender=PendingIntent.getBroadcast(addTerm.this, MainActivity.numAlert++,newIntent,0);
                AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, endTrigger, newSender);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}