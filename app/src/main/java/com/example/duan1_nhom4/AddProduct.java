package com.example.duan1_nhom4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_nhom4.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProduct extends AppCompatActivity {
    Button btnInsert;
    EditText edtName;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        btnInsert = findViewById(R.id.btnInsert);
        edtName = findViewById(R.id.edtName);
        database = FirebaseDatabase.getInstance().getReference();
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();
            }
        });
    }


    private void InsertData() {
        String name = edtName.getText().toString();
        String id = database.push().getKey();
        Product product = new Product(name);

        database.child("products").child(id).setValue(product)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AddProduct.this, "Products Details Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
    });
    }
}