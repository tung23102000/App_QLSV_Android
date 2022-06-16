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
import com.example.projectcuoiky.model.Class;

public class UpdateAccountActivity extends AppCompatActivity implements View.OnClickListener{
  private Button btnUpdate,btnCancel;
 // private EditText eOldPass;
  private EditText eNewPass, eConfirmPass;
private  SQLiteHelper db;
  private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        initView();
        btnUpdate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        Intent t = getIntent();
        account=(Account) t.getSerializableExtra("account");

    }

    private void initView() {
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate=findViewById(R.id.btnSave);
      //  eOldPass = findViewById(R.id.txtPassword);
        eNewPass = findViewById(R.id.txtNewPassword);
        eConfirmPass = findViewById(R.id.txtNewPasswordConfirm);
    }
    @Override
    public void onClick(View view) {
        if(view==btnCancel){
            finish();
        }
        if(view==btnUpdate){

            String newPass = eNewPass.getText().toString();
            String newPassConfirm = eConfirmPass.getText().toString();
            if(newPassConfirm.equals(newPass)){
               SQLiteHelper db = new SQLiteHelper(this);
                String username = account.getUsername();
                Account account1 = new Account(username,newPass);
                db.changePassword(account1);
                finish();

            }
            else {
                Toast.makeText(this,"Nhập không khớp với mật khẩu",Toast.LENGTH_SHORT).show();
            }


        }
    }
}