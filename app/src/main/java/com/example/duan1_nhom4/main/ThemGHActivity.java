package com.example.duan1_nhom4.main;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.duan1_nhom4.GioHangActivity;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.SlideAdapter;
import com.example.duan1_nhom4.adapter.HomeAdapter;
import com.example.duan1_nhom4.fragmentadmin.AddFood;
import com.example.duan1_nhom4.model.GioHang;
import com.example.duan1_nhom4.model.Product;
import com.example.duan1_nhom4.model.SlideIten;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ThemGHActivity extends AppCompatActivity {
    TextView tensp,giasp,mota,numberOderTxt;
    Integer numberOder = 1;
    ImageView hinhsp;
    Button themvaogh;

    ImageView plussp,minussp;

    private DatabaseReference addFood = FirebaseDatabase.getInstance().getReference("GioHang");
    String ghID = addFood.push().getKey();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ghactivity);
        tensp = findViewById(R.id.product_tensp);
        giasp = findViewById(R.id.product_giasp);
        mota = findViewById(R.id.product_motasp);
        hinhsp = findViewById(R.id.product_image_view);
        numberOderTxt = findViewById(R.id.product_sosp);
        plussp = findViewById(R.id.product_themsp);
        minussp = findViewById(R.id.product_botsp);
        themvaogh = findViewById(R.id.btnThemGH);

        Product product = (Product) getIntent().getSerializableExtra("chitiet");
        tensp.setText(product.ten);
        giasp.setText(product.gia);
        mota.setText(product.mota);
        numberOderTxt.setText(String.valueOf(numberOder));
        Glide.with(getApplicationContext()).load(product.hinh).into(hinhsp);
        plussp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOder = numberOder + 1;
                numberOderTxt.setText(String.valueOf(numberOder));
            }
        });
        minussp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOder>1){
                    numberOder=numberOder - 1;
                }
                numberOderTxt.setText(String.valueOf(numberOder));
            }
        });
        themvaogh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy thông tin từ các trường dữ liệu
                String ten = tensp.getText().toString().trim();
                String gia = giasp.getText().toString().trim();
                String soluong = numberOder.toString().trim();
                String imageUrl = product.hinh; // Sử dụng đường dẫn hình ảnh từ đối tượng Product

                // Kiểm tra nếu có trường nào đó trống
                if (ten.isEmpty() || gia.isEmpty() || soluong.isEmpty() || imageUrl.isEmpty()) {
                    Toast.makeText(ThemGHActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo đối tượng GioHang
                GioHang gioHang = new GioHang(imageUrl, ten, gia, soluong,ghID,0);

                // Thực hiện thêm đối tượng GioHang vào Firebase Database
                addFood.child(ghID).setValue(gioHang)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Thêm thành công
                                Toast.makeText(ThemGHActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();

                                // Tùy chỉnh phần xử lý sau khi thêm vào Firebase nếu cần

                                // Đặt lại các trường dữ liệu hoặc làm sạch UI nếu cần
                                tensp.setText("");
                                giasp.setText("");
                                mota.setText("");
                                numberOder = 1;
                                numberOderTxt.setText(String.valueOf(numberOder));
                                hinhsp.setImageResource(R.drawable.logo);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Gặp lỗi khi thêm vào Firebase
                                Toast.makeText(ThemGHActivity.this, "Lỗi khi thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


}}
