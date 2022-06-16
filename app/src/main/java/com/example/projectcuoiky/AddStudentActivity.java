package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Student;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener{
 private Button btnCancel, btnSave;
 private EditText eStudentCode, eName, eDate;
    private RadioButton rNam,rNu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initView();
        // nhận dữ liệu(giá trị idClass) truyền sang từ bên adapterClass -> AddStudentActivity
        Intent intent = getIntent();
       int idClass= intent.getIntExtra("idClass",0);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void initView() {
        eStudentCode=findViewById(R.id.eStudentCode);
        eName = findViewById(R.id.eName);
        eDate = findViewById(R.id.eDate);
        btnSave= findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
        rNam = findViewById(R.id.radiobtnNam);
        rNu= findViewById(R.id.radiobtnNu);
    }

    @Override
    public void onClick(View view) {
        if(view==btnCancel){
            finish();
        }
        if(view==btnSave){
            Intent intent = getIntent();
            int idClass= intent.getIntExtra("idClass",0);
            String studentCode = eStudentCode.getText().toString();
            String name = eName.getText().toString();
            String date= eDate.getText().toString();
            String gender ="";
            if(rNam.isChecked()){
                gender= rNam.getText().toString();
            }
            else{
                gender= rNu.getText().toString();
            }
            Student student = new Student(studentCode,name,date,gender,idClass);
            SQLiteHelper db = new SQLiteHelper(this);
            db.addStudent(student);
            finish();
        }
    }
}