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

public class Forgotpassword extends AppCompatActivity {
    private EditText edtEmailQuen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        TextView btnbackforgot = findViewById(R.id.btnbackforgot);
        btnbackforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forgotpassword.this, LoginApp.class);
                startActivity(intent);
            }
        });

        edtEmailQuen = findViewById(R.id.edtEmailQuen);

        findViewById(R.id.btnRecoverPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmailQuen.getText().toString().trim();
                if (!TextUtils.isEmpty(email)) {
                    // Implement Firebase logic to send recovery email and navigate to OTP verification page
                    sendRecoveryEmail(email);
                } else {
                    Toast.makeText(Forgotpassword.this, "Enter your registered email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendRecoveryEmail(String email) {
        // Implement Firebase logic to send a recovery email
        // Navigate to OTP verification page
        Intent intent = new Intent(Forgotpassword.this, OTPpassword.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}