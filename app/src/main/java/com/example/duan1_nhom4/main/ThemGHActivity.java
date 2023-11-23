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
import android.widget.Button;
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
    TextView tensp,giasp,mota,sosp;
    ImageView hinhsp;
    Button themsp,xoasp,themvaogh;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("products");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ghactivity);
        tensp = findViewById(R.id.product_tensp);
        giasp = findViewById(R.id.product_giasp);
        mota = findViewById(R.id.product_motasp);
        sosp = findViewById(R.id.product_sosp);
        hinhsp = findViewById(R.id.product_image_view);
        themsp = findViewById(R.id.product_themsp);
        xoasp = findViewById(R.id.product_botsp);
        themvaogh = findViewById(R.id.btnThemGH);

        Product product = (Product) getIntent().getSerializableExtra("chitiet");
        tensp.setText(product.ten);
        giasp.setText(product.gia);
        mota.setText(product.mota);
        Glide.with(getApplicationContext()).load(product.hinh).into(hinhsp);
    }
}