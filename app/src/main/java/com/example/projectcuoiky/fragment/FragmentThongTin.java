package com.example.projectcuoiky.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectcuoiky.LoginActivity;
import com.example.projectcuoiky.MainActivity;
import com.example.projectcuoiky.R;
import com.example.projectcuoiky.UpdateAccountActivity;
import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Account;

import java.util.ArrayList;
import java.util.List;

public class FragmentThongTin extends Fragment {
    private Button btnUpdate;
    private Button btnLogout;
  private Account account;
   private MainActivity mMainActivity;
   private TextView txtName, txtUser, txtGmail;
   private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_thongtin,container,false);
       // mMainActivity = (MainActivity) getActivity();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent t = ((MainActivity)getActivity()).getIntent();
       account=(Account) t.getSerializableExtra("account");
        txtName = view.findViewById(R.id.tvName);
        txtUser = view.findViewById(R.id.tvUsername);
        txtGmail=view.findViewById(R.id.tvMail);
     btnUpdate = view.findViewById(R.id.btnUpdateProfile);
         btnLogout = view.findViewById(R.id.btnLogout);

        txtName.setText(account.getName());
        txtUser.setText(account.getUsername());
        txtGmail.setText(account.getGmail());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent t = new Intent(getContext(), UpdateAccountActivity.class);
                  t.putExtra("account",account);
                  startActivity(t);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        List<Account> accounts = new ArrayList<>();
//        accounts = db.getUser();

    }
}
