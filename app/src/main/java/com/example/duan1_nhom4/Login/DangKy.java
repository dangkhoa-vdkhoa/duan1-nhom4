package com.example.duan1_nhom4.Login;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.example.duan1_nhom4.main.MainActivity;
import com.example.duan1_nhom4.model.Username;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class DangKy extends AppCompatActivity {
    GoogleSignInClient mggsu;

    private FirebaseAuth mAthur;

    EditText edthotendangky;
    EditText edtEmail;
    EditText edtpass;
    EditText edtpassreturn;
    Button btndangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        mAthur = FirebaseAuth.getInstance();

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

        TextView btnbacklogin = findViewById(R.id.btnbacklogin);
        btnbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKy.this, LoginApp.class);
                startActivity(intent);
            }
        });
        edthotendangky = findViewById(R.id.edthotendangky);
        edtEmail = findViewById(R.id.edtEmail);
        edtpass = findViewById(R.id.edtpassdangky);
        edtpassreturn = findViewById(R.id.edtpassreturn);
        btndangky = findViewById(R.id.btndangky);

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangky();
            }
        });

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

    public void dangky(){
        String hoten = edthotendangky.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtpass.getText().toString();
        String passreturn = edtpassreturn.getText().toString();

        // Kiểm tra xem các trường thông tin đã được nhập đầy đủ hay chưa
        if (TextUtils.isEmpty(hoten) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(DangKy.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(DangKy.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
        }else if (password.length() < 6) {
            Toast.makeText(DangKy.this, "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
        }else {
            if (!password.equals(passreturn)){
                Toast.makeText(DangKy.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            }else {
                mAthur.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAthur.getCurrentUser();
                            Username newUser = new Username(hoten, email);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(user.getUid())
                                    .setValue(newUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(DangKy.this, "Đăng ký thành công ", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(DangKy.this, LoginApp.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                           });
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(DangKy.this, "Email đã được sử dụng bởi người dùng khác", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.w(TAG, "createUserWithEmailAndPassword:failure", task.getException());
                                Toast.makeText(DangKy.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        }
    }



}