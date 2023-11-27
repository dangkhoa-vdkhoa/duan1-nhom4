package com.example.duan1_nhom4.fragmentadmin;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.model.Thongbao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddThongBao extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button btnUploadThongBao, btnBackThongBao;

    private EditText edtTenThongBao, edtNoiDungThongbao;

    private TextView imghinh;

    private TextView tvDateThongBaoadd;

//    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("ThongBao");
    private DatabaseReference thongBaoReference = FirebaseDatabase.getInstance().getReference("ThongBao");
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thong_bao);

        btnUploadThongBao = findViewById(R.id.btnUploadThongBao);
        btnBackThongBao = findViewById(R.id.btnBackThongBao);
        edtTenThongBao = findViewById(R.id.edtTenThongBao);
        edtNoiDungThongbao = findViewById(R.id.edtNoiDungThongBao);
        tvDateThongBaoadd = findViewById(R.id.tvDateThongBAOadd);
        imghinh = findViewById(R.id.imghinh);

        btnBackThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnUploadThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToFirebase();
            }
        });


        tvDateThongBaoadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePickerDialog();
            }
        });


    }

    private void ShowDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddThongBao.this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String curren = dayOfMonth + "/" + month + "/" + year;
        tvDateThongBaoadd.setText(curren);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void uploadToFirebase() {
        String ten = edtTenThongBao.getText().toString().trim();
        String noidung = edtNoiDungThongbao.getText().toString().trim();
        String hinh = imghinh.getText().toString().trim();
        String date = tvDateThongBaoadd.getText().toString().trim();

        if (ten.isEmpty() || noidung.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            Thongbao thongbao = new Thongbao(ten, noidung, date);
            String thongBaoId = thongBaoReference.push().getKey();

            if (thongBaoId != null) {
                thongBaoReference.child(thongBaoId)
                        .setValue(thongbao)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(AddThongBao.this, "Đăng thông báo thành công", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
//                                    Intent intent = new Intent(AddThongBao.this, ThongBaoAdmin.class);
//                                    startActivity(intent);
//                                    finish();
                                } else {
                                    Toast.makeText(AddThongBao.this, "Đăng thông báo thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }
}