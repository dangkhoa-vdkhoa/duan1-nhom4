package com.example.duan1_nhom4.Login;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_nhom4.MainActivity;
import com.example.duan1_nhom4.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginApp extends AppCompatActivity {
    GoogleSignInClient mgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        TextView tvdangky = findViewById(R.id.tvdangky);
        tvdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginApp.this, DangKy.class);
                startActivity(intent);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sig = mgg.getSignInIntent();
                rsactivity_google.launch(sig);
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mgg = GoogleSignIn.getClient(this,gso);
    }
    ActivityResultLauncher<Intent> rsactivity_google = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK){
                try {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    String email = account.getEmail();
                    String name = account.getDisplayName();
                    Toast.makeText(LoginApp.this, "Đăng nhập thành công "+"\n"+"email: "+ email+"\n"+"name: "+name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginApp.this, MainActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Log.e(TAG,"onFailure: ",e);
                }
            }else {
                Toast.makeText(LoginApp.this, "Thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    });
}