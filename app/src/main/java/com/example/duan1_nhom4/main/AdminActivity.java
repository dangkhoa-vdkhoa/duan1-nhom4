package com.example.duan1_nhom4.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.fragment.DonHangFragment;
import com.example.duan1_nhom4.fragment.HomeFragment;
import com.example.duan1_nhom4.fragmentadmin.HomeFragmentAdmin;
import com.example.duan1_nhom4.fragmentadmin.ThongBaoAdmin;
import com.example.duan1_nhom4.fragmentadmin.Toiadmin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationViewAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        replaceFragment(new HomeFragmentAdmin());
        bottomNavigationViewAdmin = findViewById(R.id.bottomNavigationViewAdmin);

        bottomNavigationViewAdmin.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            }else if (item.getItemId() == R.id.order){
                replaceFragment(new DonHangFragment());
            }else if (item.getItemId() == R.id.profile){
                replaceFragment(new Toiadmin());
            } else if (item.getItemId() == R.id.notifi) {
                replaceFragment(new ThongBaoAdmin()) ;
            }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layoutAdmin,fragment);
        fragmentTransaction.commit();
    }
}