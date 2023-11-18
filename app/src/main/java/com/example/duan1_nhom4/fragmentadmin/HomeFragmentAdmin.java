package com.example.duan1_nhom4.fragmentadmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duan1_nhom4.Login.LoginApp;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.SlideAdapter;
import com.example.duan1_nhom4.adapter.AdapterProduct;
import com.example.duan1_nhom4.fragment.ToiFragment;
import com.example.duan1_nhom4.model.Product;
import com.example.duan1_nhom4.model.SlideIten;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragmentAdmin extends Fragment {

    ViewPager2 viewPager2;
    RecyclerView recyclerView;
    DatabaseReference database;
    AdapterProduct myAdapter;
    ArrayList<Product> list;

    ImageView ivProfile;

    FirebaseUser currentUser;
    public HomeFragmentAdmin() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);

//        ivProfile = view.findViewById(R.id.ivProfile);
//
//        viewPager2 = view.findViewById(R.id.viewPager);
//
//        List<SlideIten> slideIten = new ArrayList<>();
//        slideIten.add(new SlideIten(R.drawable.hinh1));
//        slideIten.add(new SlideIten(R.drawable.hinh2));
//        slideIten.add(new SlideIten(R.drawable.hinh1));
//
//        viewPager2.setAdapter(new SlideAdapter(slideIten,viewPager2));
//
//        recyclerView = view.findViewById(R.id.recyclerView);
//        database = FirebaseDatabase.getInstance().getReference("products");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),2));
//
//        list = new ArrayList<Product>();
//        myAdapter = new AdapterProduct(requireContext(),list);
//        recyclerView.setAdapter(myAdapter);
//
//
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Product product = dataSnapshot.getValue(Product.class);
//                    list.add(product);
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        return view;
    }

}