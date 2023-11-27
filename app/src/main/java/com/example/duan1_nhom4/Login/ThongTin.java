package com.example.duan1_nhom4.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.model.ThongTinUsers;
import com.example.duan1_nhom4.model.Username;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;

public class ThongTin extends AppCompatActivity {

    TextView tvtentk,tvemailtk,tvsdttk,tvdiachitk,btnbackthongtin;

    Button btnUpdateThongTin;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        tvtentk = findViewById(R.id.tvtentk);
        tvemailtk = findViewById(R.id.tvenmailtk);
        tvsdttk = findViewById(R.id.tvsdttk);
        tvdiachitk = findViewById(R.id.tvdiachitk);
        btnUpdateThongTin = findViewById(R.id.btnUpdateThongTin);
        btnbackthongtin = findViewById(R.id.btnbackthongtin);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            tvemailtk.setText(userEmail);

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid());
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Username user = snapshot.getValue(Username.class);
                        ThongTinUsers thongTinUsers = snapshot.getValue(ThongTinUsers.class);
                        if (user != null || thongTinUsers != null) {
                            String userName = user.getHoten();
                            tvtentk.setText(userName);

                            String sodienthoai = thongTinUsers.getSdt();
                            tvsdttk.setText(sodienthoai);

                            String diachind = thongTinUsers.getDiachi();
                            tvdiachitk.setText(diachind);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        btnUpdateThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTin.this,UpdateThongTin.class);
                startActivity(intent);
            }
        });
        btnbackthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
}