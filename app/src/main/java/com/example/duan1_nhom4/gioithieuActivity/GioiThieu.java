package com.example.duan1_nhom4.gioithieuActivity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.main.MainActivity;
import com.example.duan1_nhom4.main.WelcomeScreen;
import com.ramotion.paperonboarding.PaperOnboardingEngine;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class GioiThieu extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ramotion.paperonboarding.R.layout.onboarding_main_layout);

        PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(com.ramotion.paperonboarding.R.id.onboardingRootView),
                getPaperOnboardingPage(),this);

        // Kiểm tra trạng thái onboarding
        if (isOnboardingCompleted()) {
            // Nếu đã hoàn thành, không hiển thị onboarding và chuyển đến màn hình chính
            startActivity(new Intent(GioiThieu.this, MainActivity.class));
            finish();
        } else {
            // Nếu chưa hoàn thành, hiển thị onboarding
            setContentView(com.ramotion.paperonboarding.R.layout.onboarding_main_layout);
            setupOnboarding();
        }

    }
    private void setupOnboarding() {
        // Code khởi tạo onboarding engine và các sự kiện ở đây
        PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(com.ramotion.paperonboarding.R.id.onboardingRootView),
                getPaperOnboardingPage(),this);
        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                // Khi lướt đến cuối cùng, đánh dấu onboarding đã hoàn thành
                setOnboardingCompleted();

                // Chuyển đến màn hình chính
                startActivity(new Intent(GioiThieu.this, WelcomeScreen.class));
                finish();
            }
        });
    }

    private boolean isOnboardingCompleted() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean("onboarding_completed", false);
    }

    private void setOnboardingCompleted() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("onboarding_completed", true);
        editor.apply();
    }


    private ArrayList<PaperOnboardingPage> getPaperOnboardingPage(){
        PaperOnboardingPage scr1 = new PaperOnboardingPage("Miễn phí",
                "Tham gia ứng dụng để được nhận những ưu đãi",
                Color.parseColor("#FFFFCC"), R.drawable.freeship, R.drawable.footer);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Thanh toán",
                "Dễ dàng thanh toán với mọi hình thức",
                Color.parseColor("#FFFFFF"), R.drawable.thanhtoan, R.drawable.footer);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Thuận tiện",
                "Dễ dàng thanh toán với mọi hình thức",
                Color.parseColor("#9B90BC"), R.drawable.footer, R.drawable.footer);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);

        return elements;
    }
}