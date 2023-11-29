package com.example.duan1_nhom4.fragmentadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.duan1_nhom4.R;
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

import com.example.duan1_nhom4.main.AdminActivity;
import com.example.duan1_nhom4.model.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddFood extends AppCompatActivity {


    private Button uploadBtn, showAllBtn;
    private ImageView imageView;

    private Button btnChooseFile;
    private ProgressBar progressBar;

    //vars
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("products");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;

    private TextInputEditText edtTen;
    private TextInputEditText edtGia;
    private TextInputEditText edtMoTa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);


        uploadBtn = findViewById(R.id.upload_btn);
        showAllBtn = findViewById(R.id.showall_btn);
        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.imageView);
        btnChooseFile = findViewById(R.id.chooseFile_btn);
        edtTen = findViewById(R.id.edtTenSanPhamAdd);
        edtGia = findViewById(R.id.edtGiaSanPhamAdd);
        edtMoTa = findViewById(R.id.edtMoTaSanPhamAdd);

        progressBar.setVisibility(View.INVISIBLE);

        showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(AddFood.this , AdminActivity.class));
            }
        });

        btnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 2);
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                }else{
                    Toast.makeText(AddFood.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==2 && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }

    private void uploadToFirebase(Uri uri){

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String ten = edtTen.getText().toString().trim();
                        String gia = edtGia.getText().toString().trim();
                        String mota = edtMoTa.getText().toString().trim();
                        String modelId = root.push().getKey();
                        Product product = new Product(uri.toString(),ten,gia,mota,modelId);
                        root.child(modelId).setValue(product);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(AddFood.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        imageView.setImageResource(R.drawable.logo);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(AddFood.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }


}