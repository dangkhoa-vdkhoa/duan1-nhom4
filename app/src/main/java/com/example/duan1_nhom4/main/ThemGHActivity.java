package com.example.duan1_nhom4.main;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.SlideAdapter;
import com.example.duan1_nhom4.adapter.HomeAdapter;
import com.example.duan1_nhom4.model.Product;
import com.example.duan1_nhom4.model.SlideIten;
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

public class ThemGHActivity extends AppCompatActivity {
    private HomeAdapter myAdapter;
    ViewPager2 viewPager2;
    RecyclerView recyclerView;
    DatabaseReference database;
    ArrayList<Product> list;

    ImageView ivProfile;


    FirebaseUser currentUser;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("products");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ghactivity);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product model = dataSnapshot.getValue(Product.class);
                    list.add(model);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            Product product = (Product) intent.getSerializableExtra("products");

            if (product != null) {
                // Hiển thị thông tin sản phẩm trên giao diện của ThemGHActivity
                ImageView imageView = findViewById(R.id.product_image_view);
                TextView tenSPTextView = findViewById(R.id.ten_san_pham_text_view);
                TextView giaSPTextView = findViewById(R.id.gia_san_pham_text_view);

                // Sử dụng Glide hoặc thư viện tương tự để hiển thị hình ảnh từ URL
                Glide.with(this).load(product.getHinh()).into(imageView);

                tenSPTextView.setText(product.getTen());
                giaSPTextView.setText(product.getGia());
            }
        }}
}