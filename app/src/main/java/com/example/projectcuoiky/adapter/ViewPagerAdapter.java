package com.example.projectcuoiky.adapter;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.projectcuoiky.MainActivity;
import com.example.projectcuoiky.fragment.FragmentHome;
import com.example.projectcuoiky.fragment.FragmentNotification;
import com.example.projectcuoiky.fragment.FragmentSearch;
import com.example.projectcuoiky.fragment.FragmentThongTin;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {



    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHome();
            case 1: return new FragmentThongTin();
            case 2: return new FragmentSearch();
           // case 3: return new FragmentNotification();
            default: return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
