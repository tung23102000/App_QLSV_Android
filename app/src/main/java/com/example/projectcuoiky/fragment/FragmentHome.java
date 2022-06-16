package com.example.projectcuoiky.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectcuoiky.ActivityClass;
import com.example.projectcuoiky.ActivityStatic;
import com.example.projectcuoiky.ActivitySubject;
import com.example.projectcuoiky.MainActivity;
import com.example.projectcuoiky.R;

public class FragmentHome extends Fragment implements View.OnClickListener{
    private Button btnClass,btnSubject,btnStatic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnClass = view.findViewById(R.id.btnClass);
        btnSubject=view.findViewById(R.id.btnSubject);
        btnStatic=view.findViewById(R.id.btnStatic);
        btnClass.setOnClickListener(this);
        btnSubject.setOnClickListener(this);
        btnStatic.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
      if(view==btnClass){
          Intent intent = new Intent(view.getContext(), ActivityClass.class);
          startActivity(intent);
      }

       if(view==btnSubject){
          Intent intent = new Intent(view.getContext(), ActivitySubject.class);
          startActivity(intent);
      }
       if(view==btnStatic){
           Intent intent = new Intent(view.getContext(), ActivityStatic.class);
           startActivity(intent);
       }
    }
}
