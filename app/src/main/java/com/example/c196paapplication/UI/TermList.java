package com.example.c196paapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.c196paapplication.DAO.TermDAO;
import com.example.c196paapplication.Database.Repository;
import com.example.c196paapplication.Entity.Term;
import com.example.c196paapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TermList extends AppCompatActivity {
    private  Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        //setting up menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());
        List<Term> allTerms = repository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final termAdapter termadapter = new termAdapter(this);
        recyclerView.setAdapter(termadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termadapter.setTerms(allTerms);
    }

    public void onAdd(View view) {
        Intent intent = new Intent(TermList.this, addTerm.class);
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
                Intent intent = new Intent(TermList.this, TermList.class);
                startActivity(intent);
                return true;
            case R.id.course:
                Intent intent1 = new Intent(TermList.this, CourseList.class);
                startActivity(intent1);
                return true;
            case R.id.assessments:
                Intent intent2 = new Intent(TermList.this, AssessmentList.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


