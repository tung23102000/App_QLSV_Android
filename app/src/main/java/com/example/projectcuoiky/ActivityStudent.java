package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projectcuoiky.adapter.adapterStudent;
import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityStudent extends AppCompatActivity implements adapterStudent.ItemListener{
    private RecyclerView recyclerView;
    private SearchView searchView;
    private adapterStudent adapter;
    private FloatingActionButton floatingActionButton;
    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Intent intent = getIntent();
        int idClass=intent.getIntExtra("idClass",0);
        recyclerView = findViewById(R.id.recycleViewStudent);
        searchView = findViewById(R.id.searchStudent);
        adapter = new adapterStudent();
        floatingActionButton = findViewById(R.id.fabStu);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityStudent.this,AddStudentActivity.class);
                intent.putExtra("idClass",idClass);
                startActivity(intent);
            }
        });
        db = new SQLiteHelper(getApplicationContext());
//        Student s = new Student("B18DCAT225","Nguyen Thanh Tung","23/10/2000","Nam",1);
//        Student c = new Student("B18DCAT227","Nguyen MTP2","23/10/2000","Nam",2);
//        Student v = new Student("B18DCAT226","Nguyen MTP","23/10/2000","Nữ",1);
//        db.addStudent(s);
//        db.addStudent(v);
//        db.addStudent(c);
        List<Student> list = db.getAllStudentByClass(idClass);
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               List<Student> listfilter = db.searchStudentByClass(idClass,newText);
               adapter.setList(listfilter);
                return true;
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Student s = adapter.getStudent(position);
         Intent intent = new Intent(getApplicationContext(),UpdateDeleteStudentActivity.class);
         intent.putExtra("student",s);
         startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int idClass=intent.getIntExtra("idClass",0);
        List<Student> list = db.getAllStudentByClass(idClass);
        adapter.setList(list);
    }
}