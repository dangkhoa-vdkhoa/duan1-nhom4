package com.example.duan1_nhom4.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_nhom4.R;

public class OTPpassword extends AppCompatActivity {
    private EditText edtOTP;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otppassword);
        edtOTP = findViewById(R.id.edtOPT);

        // Retrieve the email from the intent
        userEmail = getIntent().getStringExtra("email");

        findViewById(R.id.btnVerifyOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = edtOTP.getText().toString().trim();
                if (!TextUtils.isEmpty(otp)) {
                    // Implement Firebase logic to verify OTP and navigate to reset password page
                    verifyOTPAndNavigateToResetPassword(otp);
                } else {
                    Toast.makeText(OTPpassword.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void verifyOTPAndNavigateToResetPassword(String otp) {
        // Implement Firebase logic to verify OTP
        // Navigate to Reset Password page
        Intent intent = new Intent(OTPpassword.this, Resetpassword.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }
}