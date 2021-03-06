package com.example.etour;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    TextView tv_resetPassword;
    EditText etEmail, etPassword;
    Button btnSignIn, btnSignUP;
    FirebaseAuth auth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (auth.getCurrentUser()!=null){
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();
        }*/
        setContentView(R.layout.activity_sign_in);
        tv_resetPassword = findViewById(R.id.tv_resetPassword);
        etEmail =   findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.et_Password);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUP = findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.progressBar2);

        //navigate to password reset screen
        tv_resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, PasswordResetActivity.class));
            }
        });

        auth = FirebaseAuth.getInstance();

        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnSignUp) {
                    startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
                } else {
                    throw new IllegalStateException("Unexpected value: " + v.getId());
                }

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                final  String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    etEmail.setError("email address required");
                    etEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("please enter a valid email");
                    etEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    etPassword.setError("enter yor a/c password");
                    etPassword.requestFocus();
                }
                if (password.length() < 6) {
                    etPassword.setError(getString(R.string.minimum_password));
                }
                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                    finish();
                                }
                            }

                        });
            }
        });


    }
}
