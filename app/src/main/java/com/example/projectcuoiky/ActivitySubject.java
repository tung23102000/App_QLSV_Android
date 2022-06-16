package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projectcuoiky.adapter.adapterSubject;
import com.example.projectcuoiky.dal.SQLiteHelper;

import com.example.projectcuoiky.model.Subject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
//public class ActivitySubject extends AppCompatActivity{
public class ActivitySubject extends AppCompatActivity implements adapterSubject.ItemListener {
  private RecyclerView recyclerView;
  private adapterSubject adapterSubject;
  private FloatingActionButton floatingActionButton;
  private SQLiteHelper db;
  private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        adapterSubject = new adapterSubject(this);
       recyclerView = findViewById(R.id.recycleView);
       searchView = findViewById(R.id.searchSubject);
        floatingActionButton= findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     Intent intent = new Intent(ActivitySubject.this,AddSubjectActivity.class);
                     startActivity(intent);
            }
        });
       db = new SQLiteHelper(this);
        Subject subject=new Subject("An toàn mạng","3","703-A2","Nguyễn Ngọc Điệp");
        db.addSubject(subject);
        List<Subject> listSubjects = db.getAllSubjects();
        System.out.println(listSubjects.size());
        adapterSubject.setSubjectList(listSubjects);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterSubject);
        adapterSubject.setItemListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Subject> subjectList = db.searchSubject(newText);
                adapterSubject.setSubjectList(subjectList);
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
      List<Subject> listSubject = db.getAllSubjects();
      adapterSubject.setSubjectList(listSubject);
    }

    @Override
    public void onItemClick(View view, int position) {
        // Subject sub = adapterSubject.getSubject(position);
       //  Intent intent = new Intent(getApplicationContext(),InfoSubjectActivity.class);
        // startActivity(intent);
    }
}