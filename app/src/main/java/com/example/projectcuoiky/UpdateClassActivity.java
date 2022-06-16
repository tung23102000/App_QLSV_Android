package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Class;

public class UpdateClassActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eName, eRoom, eTeacher;
    private Button btnSave, btnCancel;
  private Class c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_class);
        initView();

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        Intent intent = getIntent();
        c=(Class) intent.getSerializableExtra("class");
        eName.setText(c.getName());
       eRoom.setText(c.getRoom());
       eTeacher.setText(c.getTeacher());

    }

    private void initView() {
        eName= findViewById(R.id.eName);
        eRoom=findViewById(R.id.eRoom);
        eTeacher=findViewById(R.id.eTeacher);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
    }

    @Override
    public void onClick(View view) {
        if(view==btnCancel){
            finish();
        }
        if(view==btnSave){
            String name = eName.getText().toString();
            String room = eRoom.getText().toString();
            String teacher= eTeacher.getText().toString();
            int id = c.getId();
            Class c = new Class(id,name,room,teacher);
            SQLiteHelper db = new SQLiteHelper(this);
            db.updateClass(c);
            finish();
        }
    }
}