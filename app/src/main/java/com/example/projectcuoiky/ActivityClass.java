package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

import com.example.projectcuoiky.adapter.adapterClass;
import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Class;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityClass extends AppCompatActivity implements adapterClass.ItemListener{
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private adapterClass adapter;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        adapter=new adapterClass(this);
        recyclerView= findViewById(R.id.recycleViewClass);
        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(ActivityClass.this,AddClassActivity.class);
                 startActivity(intent);
            }
        });
        db= new SQLiteHelper(getApplicationContext());
//        Class c = new Class("D18CQAT01-B","703-A2","Nguyễn Hoàng Anh");
//        db.addClass(c);
        List<Class> list = db.getAllClasses();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Class> list = db.getAllClasses();
        adapter.setList(list);

    }

    @Override
    public void onItemClick(View view, int position) {
        Class c = adapter.getClass(position);
        Intent intent = new Intent(getApplicationContext(), ActivityStudent.class);

        int class_id = c.getId();
        intent.putExtra("idClass",class_id);

        startActivity(intent);
    }
}