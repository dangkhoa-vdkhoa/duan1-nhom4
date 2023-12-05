package com.example.duan1_nhom4.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.duan1_nhom4.Login.LoginApp;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.databinding.ActivityMainBinding;
import com.example.duan1_nhom4.fragment.DonHangFragment;
import com.example.duan1_nhom4.fragment.HomeFragment;
import com.example.duan1_nhom4.fragment.ThongBaoFragment;
import com.example.duan1_nhom4.fragment.ToiFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home){
                replaceFragment(new HomeFragment());
            }else if (item.getItemId() == R.id.notification){
                replaceFragment(new ThongBaoFragment());
            }else if (item.getItemId() == R.id.order){
                if (currentUser != null){
                    replaceFragment(new DonHangFragment());
                }else {
                    Toast.makeText(this, "Vui lòng đăng nhập !!!", Toast.LENGTH_SHORT).show();
                    Intent intents = new Intent(this, LoginApp.class);
                    startActivity(intents);
                }
            }else if (item.getItemId() == R.id.profile){
                if (currentUser != null){
                    replaceFragment(new ToiFragment());
                }else {
                    Toast.makeText(this, "Vui lòng đăng nhập để xem Thông Tin Tài Khoản", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginApp.class);
                    startActivity(intent);
                }
                replaceFragment(new ToiFragment());
            }
            return true;
        });


    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}