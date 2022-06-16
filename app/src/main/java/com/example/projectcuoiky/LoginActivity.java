package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.fragment.FragmentHome;
import com.example.projectcuoiky.fragment.FragmentThongTin;
import com.example.projectcuoiky.model.Account;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
     private Button btnLogin,btnRegister;
     private EditText eUser,ePass;
     private SQLiteHelper db;
     private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eUser=findViewById(R.id.txtUsername);
        ePass=findViewById(R.id.txtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);
        db = new SQLiteHelper(getApplicationContext());
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
     if(view==btnLogin){
           String username = eUser.getText().toString();
           String password = ePass.getText().toString();
           if(username.equals("")||password.equals("")){
               Toast.makeText(LoginActivity.this,"Vui lòng điền đầy đủ các trường",Toast.LENGTH_SHORT).show();
           } else {
               Boolean checkUserPass = db.checkUsernamePassword(username,password);
               if(checkUserPass==true){
                   Toast.makeText(LoginActivity.this,"Đăng nhập thành công!!",Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   account = db.login(username,password);
                 // account = new Account(username,password);
                 intent.putExtra("account",account);
//                   Fragment frag = new FragmentThongTin();
//                   Bundle bundle = new Bundle();
//
//                   bundle.putString("name",account.getName());
//                   bundle.putString("gmail",account.getGmail());
//                   bundle.putString("user",account.getUsername());
//
//                   frag.setArguments(bundle);
//                   FragmentTransaction
             startActivity(intent);

               }
               else{
                   Toast.makeText(LoginActivity.this,"Đăng nhập thất bại. Hãy kiểm tra lại",Toast.LENGTH_SHORT).show();
               }
           }
     }
     if(view==btnRegister){
             Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
             startActivity(intent);
     }
    }

}