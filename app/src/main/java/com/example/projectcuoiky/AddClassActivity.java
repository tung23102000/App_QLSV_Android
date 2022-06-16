package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Class;

public class AddClassActivity extends AppCompatActivity implements View.OnClickListener{
  private EditText eName,eRoom,eTeacher;
  private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        initView();
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
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
      Class c = new Class(name,room,teacher);
      SQLiteHelper db = new SQLiteHelper(this);
      db.addClass(c);
      finish();
  }
    }
}