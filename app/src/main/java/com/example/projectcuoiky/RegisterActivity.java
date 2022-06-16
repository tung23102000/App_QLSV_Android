package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Account;

public class RegisterActivity extends AppCompatActivity{
    private Button btnRegister;
    private EditText eUser,ePass,eRepass,eName,eGmail;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new SQLiteHelper(this);
        eUser=findViewById(R.id.txtUsername);
        ePass=findViewById(R.id.txtPassword);
        eName = findViewById(R.id.txtName);
        eGmail=findViewById(R.id.txtGmail);
        eRepass=findViewById(R.id.txtRePassword);
        btnRegister=findViewById(R.id.btnSave1);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eName.getText().toString();
                String gmail = eGmail.getText().toString();
                String username = eUser.getText().toString();
                String password = ePass.getText().toString();
                String rePassword = eRepass.getText().toString();
                if(username.equals("")||password.equals("")||rePassword.equals("")||name.equals("")||gmail.equals("")){
                    Toast.makeText(RegisterActivity.this,"Vui lòng điền đầy đủ các trường",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(rePassword)){
                        Boolean checkUser = db.checkUsername(username);
                        if(checkUser==false){//chưa tồn tại username
                            Account account = new Account(name,gmail,username,password);
                            db.addUser(account);
                            Toast.makeText(RegisterActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            intent.putExtra("account",account);
                            startActivity(intent);
                        } else{
                            Toast.makeText(RegisterActivity.this,"Đã tồn tại tài khoản",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this,"Nhập không khớp với mật khẩu",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}