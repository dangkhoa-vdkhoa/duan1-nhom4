package com.example.duan1_nhom4.fragmentadmin;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.duan1_nhom4.adapter.MyAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.example.duan1_nhom4.AddProduct;
import com.example.duan1_nhom4.Login.LoginApp;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.SlideAdapter;
import com.example.duan1_nhom4.adapter.AdapterProduct;
import com.example.duan1_nhom4.fragment.ToiFragment;
import com.example.duan1_nhom4.model.Product;
import com.example.duan1_nhom4.model.SlideIten;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class HomeFragmentAdmin extends Fragment {

    ProgressBar progressBar;
    private Uri imgUri;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("products");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    Button btnInsertImage;
    ViewPager2 viewPager2;
    RecyclerView recyclerView;
    DatabaseReference database;
    ArrayList<Product> list;
    EditText edtURL;
    TextInputEditText edTenFood;

    ImageView icAddFood,imgAdd;

    private MyAdapter myAdapter;


    public HomeFragmentAdmin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);


        viewPager2 = view.findViewById(R.id.viewPagerAdmin);

        List<SlideIten> slideIten = new ArrayList<>();
        slideIten.add(new SlideIten(R.drawable.hinh1));
        slideIten.add(new SlideIten(R.drawable.hinh2));
        slideIten.add(new SlideIten(R.drawable.hinh1));

        viewPager2.setAdapter(new SlideAdapter(slideIten,viewPager2));


        recyclerView = view.findViewById(R.id.recyclerViewAdmin);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();

        recyclerView.setAdapter(myAdapter);

        root = FirebaseDatabase.getInstance().getReference("Image");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),2));

        list = new ArrayList<Product>();
        myAdapter = new MyAdapter(getContext() , list);
        recyclerView.setAdapter(myAdapter);

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



        icAddFood = view.findViewById(R.id.icAddFood);
        icAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//            showDialog();
                startActivity(new Intent(getContext(),AddFood.class));
            }
        });


        return view;
    }

}
