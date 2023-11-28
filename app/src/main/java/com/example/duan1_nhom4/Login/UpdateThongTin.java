package com.example.duan1_nhom4.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_nhom4.Map;
import com.example.duan1_nhom4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateThongTin extends AppCompatActivity {

    Button btnMap,btnSaveThongTin;

    EditText edtSDT,edtDiaChi;

    TextView btnbackthongtinupdate;

    private DatabaseReference thongTinReference = FirebaseDatabase.getInstance().getReference("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_thong_tin);

        edtSDT = findViewById(R.id.edtSDT);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnSaveThongTin = findViewById(R.id.btnSaveThongTin);
        btnbackthongtinupdate = findViewById(R.id.btnbackthongtinupdate);
        btnbackthongtinupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        btnSaveThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadThongTin();
            }
        });


         btnMap = findViewById(R.id.btnMap);
         btnMap.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(UpdateThongTin.this, Map.class);
                 startActivity(intent);
             }
         });
    }

    private void uploadThongTin() {
        String sdt = edtSDT.getText().toString().trim();
        String diachi = edtDiaChi.getText().toString().trim();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (sdt.isEmpty() || diachi.isEmpty() ) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            String userId = currentUser.getUid();
            DatabaseReference userReference = thongTinReference.child(userId);

            HashMap<String, Object> updateData = new HashMap<>();
            updateData.put("sdt", sdt);
            updateData.put("diachi", diachi);
            userReference.updateChildren(updateData).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(UpdateThongTin.this, "Đăng thông tin thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateThongTin.this,ThongTin.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(UpdateThongTin.this, "Đăng thông tin thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
//            ThongTinUsers thongTinUsers = new ThongTinUsers(sdt,diachi);
//            String thongTinId = thongTinReference.push().getKey();
//
//            if (thongTinId != null) {
//                thongTinReference.child(thongTinId)
//                        .setValue(thongTinUsers)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(ThongTin.this, "Đăng thông báo thành công", Toast.LENGTH_SHORT).show();
////                                    onBackPressed();
//                                } else {
//                                    Toast.makeText(ThongTin.this, "Đăng thông báo thất bại", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
            }
        }
    }

