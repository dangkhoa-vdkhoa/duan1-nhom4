package com.example.duan1_nhom4.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom4.R;

public class OTPpassword extends AppCompatActivity {
    private EditText edtOTP;
    private String userEmail;

    private String generatedOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otppassword);


        edtOTP = findViewById(R.id.edtOPT);
        userEmail = getIntent().getStringExtra("email");
        generatedOTP = getIntent().getStringExtra("otp");

        findViewById(R.id.btnVerifyOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredOTP = edtOTP.getText().toString().trim();
                String userEmail = getIntent().getStringExtra("email");
                if (!TextUtils.isEmpty(enteredOTP) && enteredOTP.equals(generatedOTP)) {
                    Intent intent = new Intent(OTPpassword.this, Resetpassword.class);
                    intent.putExtra("email", userEmail);
                    startActivity(intent);
                } else {
                    Toast.makeText(OTPpassword.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView btnbackotp = findViewById(R.id.btnbackotp);

        btnbackotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OTPpassword.this, Forgotpassword.class);
                startActivity(intent);
            }
        });

        // Retrieve the email from the intent
        userEmail = getIntent().getStringExtra("email");


    }

}