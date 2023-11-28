package com.example.duan1_nhom4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duan1_nhom4.Login.LoginApp;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.SlideAdapter;
import com.example.duan1_nhom4.adapter.HomeAdapter;
import com.example.duan1_nhom4.model.Product;
import com.example.duan1_nhom4.model.SlideIten;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }
    private HomeAdapter myAdapter;
    ViewPager2 viewPager2;
    RecyclerView recyclerView;
    ArrayList<Product> list;
    SearchView searchView;
    ImageView ivProfile;
    FirebaseUser currentUser;

    private Handler handler = new Handler();
    private Runnable runnable;
    private Timer timer;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("products");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        searchView = view.findViewById(R.id.searchView);
        ivProfile = view.findViewById(R.id.ivProfile);

        viewPager2 = view.findViewById(R.id.viewPager);

        List<SlideIten> slideIten = new ArrayList<>();
        slideIten.add(new SlideIten(R.drawable.hinh1));
        slideIten.add(new SlideIten(R.drawable.hinh2));
        slideIten.add(new SlideIten(R.drawable.hinh1));
        startAutoScroll(3000);

        viewPager2.setAdapter(new SlideAdapter(slideIten,viewPager2));

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();

        recyclerView.setAdapter(myAdapter);

        root = FirebaseDatabase.getInstance().getReference("products");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),2));

        list = new ArrayList<Product>();
        myAdapter = new HomeAdapter(getContext() , list);
        recyclerView.setAdapter(myAdapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Clear the previous list items

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Product model = dataSnapshot.getValue(Product.class);
                        list.add(model);
                    }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser != null) {
                    replaceFragmenthome(new ToiFragment());
                } else {
                    Toast.makeText(requireContext(), "Vui lòng đăng nhập để xem thông tin tài khoản", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(requireContext(), LoginApp.class);
                    startActivity(intent);
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                timkiemSP(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                timkiemSP(s);
                return false;
            }
        });


        return view;
    }

    private void timkiemSP(String searchText) {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Clear the previous list items

                if (searchText.isEmpty()) {
                    // If the search text is empty, display all products
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Product model = dataSnapshot.getValue(Product.class);
                        list.add(model);
                    }
                } else if (searchText==null) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Product model = dataSnapshot.getValue(Product.class);
                        list.add(model);
                    }
                } else {
                    // If there is search text, display matching products
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Product model = dataSnapshot.getValue(Product.class);
                        if (model.getTen().toLowerCase().contains(searchText.toLowerCase())) {
                            list.add(model);
                        }
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });
    }

    private void replaceFragmenthome(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
    private void startAutoScroll(int delay) {
        runnable = () -> {
            int currentItem = viewPager2.getCurrentItem();
            int totalItems = 3;

            if (currentItem < totalItems - 1) {
                viewPager2.setCurrentItem(currentItem + 1);
            } else {
                viewPager2.setCurrentItem(0);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, delay, delay);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopAutoScroll();
    }

    private void stopAutoScroll() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}