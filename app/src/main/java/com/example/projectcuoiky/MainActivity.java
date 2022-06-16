package com.example.projectcuoiky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.projectcuoiky.adapter.ViewPagerAdapter;
import com.example.projectcuoiky.fragment.FragmentThongTin;
import com.example.projectcuoiky.model.Account;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private Account account;
  //  private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavi);
        viewPager = findViewById(R.id.viewPager);
//        floatingActionButton = findViewById(R.id.fab);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        Intent intent = getIntent();
       account=(Account) intent.getSerializableExtra("account");
     //  sendDataToInfoFrag();
//       Intent intent1 = new Intent(MainActivity.this, FragmentThongTin.class);
//       intent1.putExtra("account",account);
//       startActivity(intent1);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: bottomNavigationView.getMenu().findItem(R.id.mHome).setChecked(true); break;
                    case 1: bottomNavigationView.getMenu().findItem(R.id.mInfo).setChecked(true); break;
                    case 2: bottomNavigationView.getMenu().findItem(R.id.mSearch).setChecked(true); break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mHome: viewPager.setCurrentItem(0); break;
                    case R.id.mInfo: viewPager.setCurrentItem(1); break;
                    case R.id.mSearch: viewPager.setCurrentItem(2); break;
                }
                return true;
            }
        });
    }

    public Account getAccount() {
        return account;
    }
//    public void sendDataToInfoFrag(){
//        FragmentThongTin fragmentThongTin = new FragmentThongTin();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        Bundle data = new Bundle();
//        data.putSerializable("account",account);
//        fragmentThongTin.setArguments(data);
//        fragmentTransaction.replace(R.id.fgThongtin,fragmentThongTin).commit();
//
//
//    }

}