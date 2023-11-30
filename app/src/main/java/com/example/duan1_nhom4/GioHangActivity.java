package com.example.duan1_nhom4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.duan1_nhom4.adapter.GioHangAdapter;
import com.example.duan1_nhom4.adapter.HomeAdapter;
import com.example.duan1_nhom4.fragment.ToiFragment;
import com.example.duan1_nhom4.model.GioHang;
import com.example.duan1_nhom4.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private GioHangAdapter gioHangAdapter;
    ArrayList<GioHang> list;

    ImageView back;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("GioHang");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        recyclerView = findViewById(R.id.recyclerViewGioHang);
        back = findViewById(R.id.btnBack);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        gioHangAdapter = new GioHangAdapter(this , list);
        recyclerView.setAdapter(gioHangAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    GioHang model = dataSnapshot.getValue(GioHang.class);
                    list.add(model);
                }
                gioHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}