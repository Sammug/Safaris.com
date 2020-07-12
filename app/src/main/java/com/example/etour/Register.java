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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private TextView tv_Email;
    EditText et_confPassword, et_Password;
    Button btnRegister;
    ProgressBar progressBar;
    private  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_Email = findViewById(R.id.tv_Email);
        TextView tv_Login = findViewById(R.id.tv_Login);
        et_Password = findViewById(R.id.et_Password);
        et_confPassword = findViewById(R.id.et_confPassword);
        btnRegister = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressBar);

        tv_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, SignIn.class));
            }
        });

        //get firebase authentication
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = tv_Email.getText().toString().trim();
                String password = et_Password.getText().toString().trim();
                String repeatPassword = et_confPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    tv_Email.setError("email required");
                    tv_Email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    tv_Email.setError("please enter a valid email");
                    tv_Email.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    et_Password.setError("password is required");
                    et_Password.requestFocus();
                    return;

                }
                if (password.length() < 6) {
                    et_Password.setError("Password too short");
                    return;
                }
                if (repeatPassword.isEmpty()) {
                    et_confPassword.setError("confirm your password");
                    et_confPassword.requestFocus();
                    return;

                }
                if (!password.equals(repeatPassword)){
                    et_confPassword.setError("wrong password confirmation");
                    et_confPassword.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //et_confPassword.setError("wrong password confirmation");
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "user successfully added", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Register.this, SignIn.class));
                                    finish();
                                }

                            }
                        });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        updateUI(firebaseUser);
    }

    private void updateUI(FirebaseUser firebaseUser) {
        if (mAuth.getCurrentUser() !=null){
            startActivity(new Intent(Register.this, MainActivity.class));
            finish();
        }
    }
}
