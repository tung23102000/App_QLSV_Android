package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Student;

import java.util.List;

public class UpdateDeleteStudentActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnCancel, btnSave,btnDelete;
    private EditText eStudentCode, eName, eDate;
    private RadioButton rNam,rNu;
    private Student s;
    private List<Student> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_student);
        initView();
        Intent intent = getIntent();
       s=(Student) intent.getSerializableExtra("student");
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        eStudentCode.setText(s.getStudentCode());
        eName.setText(s.getName());
        eDate.setText(s.getDate());
       if(s.getGender().equalsIgnoreCase(rNam.getText().toString())){
           rNam.setChecked(true);
       }
       else{
           rNu.setChecked(true);
       }
    }

    private void initView() {
        eStudentCode=findViewById(R.id.eStudentCode);
        eName = findViewById(R.id.eName);
        eDate = findViewById(R.id.eDate);
        btnSave= findViewById(R.id.btnSave);
        btnDelete=findViewById(R.id.btnDelete);
        btnCancel=findViewById(R.id.btnCancel);
        rNam = findViewById(R.id.radiobtnNam);
        rNu= findViewById(R.id.radiobtnNu);
    }

    @Override
    public void onClick(View view) {
      if(view==btnCancel){
          finish();
      }
      if(view==btnDelete){

//          AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//          builder.setTitle("Thông báo xóa");
//          builder.setMessage("Bạn có muốn xóa sinh viên "+ s.getName()+" này không?");
//          builder.setIcon(R.drawable.ic_baseline_clear_24);
//          builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//              @Override
//              public void onClick(DialogInterface dialogInterface, int i) {
                  int idStudent = s.getId();
                  SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                  db.deleteStudent(idStudent);
             //     list.remove(i);

                  finish();
//              }
//          });
//          builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
//              @Override
//              public void onClick(DialogInterface dialogInterface, int i) {
//
//              }
//          });
//          AlertDialog dialog = builder.create();
//          dialog.show();
      }
      if(view==btnSave){
          String studentCode= eStudentCode.getText().toString();
          String name = eName.getText().toString();
          String date = eDate.getText().toString();
          String gender = "";
          if(rNam.isChecked()){
              gender = rNam.getText().toString();
          }
          else{
              gender = rNu.getText().toString();
          }
          int id = s.getId();
          int idClass = s.getIdClass();
          Student student = new Student(id,studentCode,name,date,gender,idClass);
          SQLiteHelper db = new SQLiteHelper(getApplicationContext());
          db.updateStudent(student);
          finish();
      }
    }
}