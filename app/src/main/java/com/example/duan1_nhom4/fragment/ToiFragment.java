package com.example.duan1_nhom4.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duan1_nhom4.Login.LoginApp;
import com.example.duan1_nhom4.R;

public class ToiFragment extends Fragment {
    View btnToiVocher;
    View btnToiDiaChi;
    View btnToiDMK;
    View btnToiDaThich;
    View btnToiGioHang;
    View btnToiLogOut;

    public ToiFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toi, container, false);
        btnToiVocher = view.findViewById(R.id.btnToiVocher);
        btnToiDiaChi = view.findViewById(R.id.btnToiDiaChi);
        btnToiDaThich = view.findViewById(R.id.btnToiDaThich);
        btnToiDMK = view.findViewById(R.id.btnToiDMK);
        btnToiGioHang = view.findViewById(R.id.btnToiGioHang);
        btnToiLogOut = view.findViewById(R.id.btnToiLogOut);

        btnToiVocher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Vocher của bạn", Toast.LENGTH_SHORT).show();
            }
        });

        btnToiDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Địa chỉ", Toast.LENGTH_SHORT).show();
            }
        });
        btnToiDaThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Đã thích", Toast.LENGTH_SHORT).show();
            }
        });
        btnToiDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Đổi mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
        btnToiGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Giỏ hàng", Toast.LENGTH_SHORT).show();
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