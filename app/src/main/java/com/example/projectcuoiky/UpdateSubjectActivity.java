package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Subject;

public class UpdateSubjectActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSave,btnCancel;
    private EditText eName,eNumber,eRoom,eTeacher;
  private Subject subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);
        initView();
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        Intent intent = getIntent();
       subject= (Subject) intent.getSerializableExtra("subject");
       eName.setText(subject.getTenMon());
       eNumber.setText(subject.getSoTin());
       eRoom.setText(subject.getPhong());
       eTeacher.setText(subject.getGiaoVien());

    }

    private void initView() {
        btnSave = findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
        eName = findViewById(R.id.eName);
        eNumber=findViewById(R.id.eNumber);
        eRoom=findViewById(R.id.eRoom);
        eTeacher=findViewById(R.id.eTeacher);
    }
    @Override
    public void onClick(View view) {
        if(view==btnCancel){
            finish();
        }
        if(view==btnSave){
            String name = eName.getText().toString();
            String number= eNumber.getText().toString();
            String room = eRoom.getText().toString();
            String teacher = eTeacher.getText().toString();
            int id = subject.getId();
            Subject subject = new Subject(id,name,number,room,teacher);
            SQLiteHelper db = new SQLiteHelper(this);
            db.updateSubject(subject);
            finish();
        }
    }
}