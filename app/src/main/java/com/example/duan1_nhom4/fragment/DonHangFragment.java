package com.example.duan1_nhom4.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.adapter.ClickGioHangAdapter;
import com.example.duan1_nhom4.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DonHangFragment extends Fragment {


    public DonHangFragment() {

    }

    RecyclerView recyclerView;
    ArrayList<User> list;
    private ClickGioHangAdapter myAdapter;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("User");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewDonHang);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        myAdapter = new ClickGioHangAdapter(getContext() , list);
        recyclerView.setAdapter(myAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User model = dataSnapshot.getValue(User.class);
                    list.add(model);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}