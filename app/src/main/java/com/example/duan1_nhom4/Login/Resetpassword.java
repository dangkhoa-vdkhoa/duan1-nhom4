package com.example.duan1_nhom4.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_nhom4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Resetpassword extends AppCompatActivity {
    private EditText edtNewPassword, edtConfirmPassword, edtOldPassword;

    private Button btnChangePassword;

    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        edtOldPassword = findViewById(R.id.edtOldPassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        TextView tvquenmkreset = findViewById(R.id.tvquenmkreset);
        tvquenmkreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resetpassword.this, Forgotpassword.class);
                startActivity(intent);
            }
        });

        TextView btnbacknewpass = findViewById(R.id.btnbacknewpass);
        btnbacknewpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = edtOldPassword.getText().toString().trim();
                String newPassword = edtNewPassword.getText().toString().trim();
                String confirmPassword = edtConfirmPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(oldPassword) && !TextUtils.isEmpty(newPassword) && !TextUtils.isEmpty(confirmPassword)) {
                    if (newPassword.equals(confirmPassword)) {
                        reauthenticateUser(oldPassword, newPassword);
                    } else {
                        Toast.makeText(Resetpassword.this, "mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Resetpassword.this, "Vui Lòng Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void reauthenticateUser(String oldPassword, String newPassword) {
        AuthCredential credential = EmailAuthProvider.getCredential(currentUser.getEmail(), oldPassword);

        currentUser.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            changePassword(newPassword);
                        } else {
                            Toast.makeText(Resetpassword.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void changePassword(String newPassword) {
        if (currentUser != null) {
            currentUser.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Resetpassword.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Resetpassword.this, LoginApp.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Resetpassword.this, "Đổi thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}