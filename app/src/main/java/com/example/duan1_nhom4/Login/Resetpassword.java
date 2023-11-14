package com.example.duan1_nhom4.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Resetpassword extends AppCompatActivity {
    private EditText edtNewPassword, edtConfirmPassword;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        TextView btnbacknewpass = findViewById(R.id.btnbacknewpass);

        btnbacknewpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resetpassword.this, OTPpassword.class);
                startActivity(intent);
            }
        });

        // Retrieve the email from the intent
        userEmail = getIntent().getStringExtra("email");

        findViewById(R.id.btnChangePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = edtNewPassword.getText().toString().trim();
                String confirmPassword = edtConfirmPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(newPassword) && !TextUtils.isEmpty(confirmPassword)) {
                    if (newPassword.equals(confirmPassword)) {
                        // Implement Firebase logic to change the password
                        changePassword(newPassword);
                    } else {
                        Toast.makeText(Resetpassword.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Resetpassword.this, "Enter new password and confirm password", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void changePassword(String newPassword) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.updatePassword(newPassword)
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