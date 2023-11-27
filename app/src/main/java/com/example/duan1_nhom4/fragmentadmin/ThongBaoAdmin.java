package com.example.duan1_nhom4.fragmentadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.adapter.ThongBaoAdapter;
import com.example.duan1_nhom4.model.Thongbao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ThongBaoAdmin extends Fragment {

    public ThongBaoAdmin() {

    }

    RecyclerView recyclerViewThongBaoAdmin;
    private ThongBaoAdapter thongBaoAdapter;
    ArrayList<Thongbao> Glist;

    Button btnUploadNotiAdmin;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("ThongBao");



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_bao_admin, container, false);

        recyclerViewThongBaoAdmin = view.findViewById(R.id.recyclerViewThongbaoAdmin);
        recyclerViewThongBaoAdmin.setHasFixedSize(true);

        recyclerViewThongBaoAdmin.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        Glist = new ArrayList<>();
        thongBaoAdapter = new ThongBaoAdapter(Glist, getContext());
        recyclerViewThongBaoAdmin.setAdapter(thongBaoAdapter);

        root = FirebaseDatabase.getInstance().getReference("ThongBao");

//        recyclerViewThongBaoAdmin.setLayoutManager(new LinearLayoutManager(getContext()));
//        Glist = new ArrayList<>();
//
//        recyclerViewThongBaoAdmin.setAdapter(thongBaoAdapter);
//
//        root = FirebaseDatabase.getInstance().getReference("ThongBao");
//        recyclerViewThongBaoAdmin.setHasFixedSize(true);
//        recyclerViewThongBaoAdmin.setLayoutManager(new GridLayoutManager(requireContext(),2));
//
//        Glist = new ArrayList<Thongbao>();
//        thongBaoAdapter = new ThongBaoAdapter(Glist, getContext());
//        recyclerViewThongBaoAdmin.setAdapter(thongBaoAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Glist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Thongbao model = dataSnapshot.getValue(Thongbao.class);
                    Glist.add(model);
                }
                thongBaoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnUploadNotiAdmin = view.findViewById(R.id.btnUploadNotiAdmin);
        btnUploadNotiAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddThongBao.class));
            }
        });
        return view;
    }
    private void replaceFragmenthome(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}