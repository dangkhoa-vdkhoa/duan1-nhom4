package com.example.duan1_nhom4.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.databinding.ActivityMainBinding;
import com.example.duan1_nhom4.fragment.DonHangFragment;
import com.example.duan1_nhom4.fragment.HomeFragment;
import com.example.duan1_nhom4.fragment.ThongBaoFragment;
import com.example.duan1_nhom4.fragment.ToiFragment;
import com.example.duan1_nhom4.fragmentadmin.HomeFragmentAdmin;

public class AdminActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        replaceFragment(new HomeFragment());
//
//        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
//            if (item.getItemId() == R.id.home) {
//                replaceFragment(new HomeFragment());
//            }else if (item.getItemId() == R.id.order){
//                replaceFragment(new DonHangFragment());
//            }else if (item.getItemId() == R.id.profile){
//                replaceFragment(new ToiFragment());
//            }
//
//            return true;
//        });
        replaceFragment(new HomeFragmentAdmin());
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layoutAdmin,fragment);
        fragmentTransaction.commit();
    }
}