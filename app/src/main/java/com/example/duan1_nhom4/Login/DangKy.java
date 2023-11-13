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
import android.widget.Button;
import android.widget.EditText;
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

public class DangKy extends AppCompatActivity {
    GoogleSignInClient mggsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        TextView btnbacklogin = findViewById(R.id.btnbacklogin);

        SignInButton signUpButton = findViewById(R.id.sign_up_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sig = mggsu.getSignInIntent();
                rsactivity_google.launch(sig);
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mggsu = GoogleSignIn.getClient(this,gso);
        btnbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKy.this, LoginApp.class);
                startActivity(intent);
            }
        });
        EditText edtnamedangky = findViewById(R.id.edtnamedangky);
        EditText edthotendangky = findViewById(R.id.edthotendangky);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtpass = findViewById(R.id.edtpassdangky);
        EditText edtpassreturn = findViewById(R.id.edtpassreturn);
        Button btndangky = findViewById(R.id.btndangky);

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
                    Toast.makeText(DangKy.this, "Đăng nhập thành công "+"\n"+"email: "+ email+"\n"+"name: "+name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DangKy.this, MainActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Log.e(TAG,"onFailure: ",e);
                }
            }else {
                Toast.makeText(DangKy.this, "Thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    });
}