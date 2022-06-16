package com.example.projectcuoiky.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoiky.ActivityStudent;
import com.example.projectcuoiky.R;
import com.example.projectcuoiky.adapter.adapterClass;
import com.example.projectcuoiky.dal.SQLiteHelper;
import com.example.projectcuoiky.model.Class;

import java.util.List;

public class FragmentSearch extends Fragment implements adapterClass.ItemListener{
    private RecyclerView recyclerView;
    //private Button btnSearch;
    private SearchView searchView;
    private adapterClass adapterClass;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvClass);
      //  btnSearch = view.findViewById(R.id.btnSearch);
        searchView = view.findViewById(R.id.search);
        adapterClass = new adapterClass();
        db = new SQLiteHelper(getContext());
        List<Class> listClass = db.getAllClasses();
        adapterClass.setList(listClass);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterClass);
        adapterClass.setItemListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Class> list = db.searchByClass(newText);
                adapterClass.setList(list);
                return true;
            }
        });

    }


    @Override
    public void onItemClick(View view, int position) {
        Class c = adapterClass.getClass(position);
        Intent intent = new Intent(getContext(), ActivityStudent.class);

        int class_id = c.getId();
        intent.putExtra("idClass",class_id);

        startActivity(intent);
    }
}
