package com.example.duan1_nhom4.Login;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.main.AdminActivity;
import com.example.duan1_nhom4.main.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginApp extends AppCompatActivity {
    GoogleSignInClient mgg;

    private FirebaseAuth mAuthLogin;
    EditText edtnameLogin ;
    EditText edtpassLogin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app);

        edtnameLogin = findViewById(R.id.edtnameLogin);
        edtpassLogin = findViewById(R.id.edtpassLogin);
        mAuthLogin = FirebaseAuth.getInstance();
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dangnhap();
            }
        });

        TextView tvquenmk = findViewById(R.id.tvquenmk);
        tvquenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginApp.this, Forgotpassword.class);
                startActivity(intent);
            }
        });

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
                String username = edtnameLogin.getText().toString().trim();

                if (username.equals("duan01fpt@gmail.com")) {
                    Toast.makeText(LoginApp.this, "Đang đăng nhập bằng tài khoản admin", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginApp.this, AdminActivity.class));
                } else {
                    Intent sig = mgg.getSignInIntent();
                    rsactivity_google.launch(sig);
                }
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
                    checkgg(email);
                } catch (Exception e){
                    Log.e(TAG,"onFailure: ",e);
                }
            }else {
                Toast.makeText(LoginApp.this, "Thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    });


    public  void dangnhap () {
         String password = edtpassLogin.getText().toString().trim();
         String username = edtnameLogin.getText().toString().trim();

         if (username.isEmpty() || password.isEmpty()){
             Toast.makeText(this, "vui lòng điền đầy đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show();
         }else if (username.equals("duan01fpt@gmail.com")) {
             Toast.makeText(this, "Đang đăng nhập bằng tài khoản admin", Toast.LENGTH_SHORT).show();
             startActivity(new Intent(LoginApp.this, AdminActivity.class));
         } else {
             mAuthLogin.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {
                         FirebaseUser user = mAuthLogin.getCurrentUser();
                         Toast.makeText(LoginApp.this, "Đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(LoginApp.this, MainActivity.class));
                     } else {
                         Toast.makeText(LoginApp.this, "Đăng nhập thất bại\n tên tài khoản hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                     }

                 }
             });
         }

    }

    public void checkgg(String email) {
        if (email.equals("duan01fpt@gmail.com")) {
            startActivity(new Intent(LoginApp.this, AdminActivity.class));
        } else {
            uploadToFirebase(email);
            startActivity(new Intent(LoginApp.this, MainActivity.class));
        }
    }
    private void uploadToFirebase(String email) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, "defaultPassword")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuthLogin.getCurrentUser();
                        } else {
                            Toast.makeText(LoginApp.this, "Firebase account creation failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
