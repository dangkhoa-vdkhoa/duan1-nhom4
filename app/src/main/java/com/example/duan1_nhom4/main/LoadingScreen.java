package com.example.duan1_nhom4.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.gioithieuActivity.GioiThieu;

public class LoadingScreen extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingScreen.this, GioiThieu.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}