package com.example.duan1_nhom4.fragmentadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.duan1_nhom4.GioHangActivity;
import com.example.duan1_nhom4.Login.LoginApp;
import com.example.duan1_nhom4.Login.Resetpassword;
import com.example.duan1_nhom4.Map;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.model.Username;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Toiadmin extends Fragment {

    View btnToiDiaChi;
    View btnToiDMK;
    View btnToiGioHang;
    View btnToiLogOut;


    TextView tvToiEmail, tvToiName;

    private FirebaseAuth mAuth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toiadmin, container, false);
        btnToiDiaChi = view.findViewById(R.id.btnToiDiaChi);
        btnToiDMK = view.findViewById(R.id.btnToiDMK);
        btnToiGioHang = view.findViewById(R.id.btnToiGioHang);
        btnToiLogOut = view.findViewById(R.id.btnToiLogOut);

        //set tên và email
        tvToiEmail = view.findViewById(R.id.tvToiEmail);
        tvToiName = view.findViewById(R.id.tvToiNameND);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            tvToiEmail.setText(userEmail);

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid());
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Username user = snapshot.getValue(Username.class);
                        if (user != null) {
                            String userName = user.getHoten();
                            tvToiName.setText(userName);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }





        btnToiDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), Map.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Địa chỉ", Toast.LENGTH_SHORT).show();
            }
        });
        btnToiDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), Resetpassword.class);
                startActivity(intent);
            }
        });
        btnToiGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
        btnToiLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Đăng xuất", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), LoginApp.class);
                startActivity(intent);
            }
        });
        return view;
    }
}