package com.example.etour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {
    EditText etPasswordResetEmail;
    TextView tv_backNav;
    Button btnReset;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        tv_backNav = findViewById(R.id.tvBackNav);
        etPasswordResetEmail = findViewById(R.id.et_ResetPassEmail);
        btnReset = findViewById(R.id.btn_Reset);
        progressBar = findViewById(R.id.progressBar3);
        firebaseAuth = FirebaseAuth.getInstance();

        tv_backNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etPasswordResetEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Enter registered email id", Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    progressBar.setVisibility(View.VISIBLE);
                                    Toast.makeText(PasswordResetActivity.this,
                                            "An email with instructions to reset your password has been sent",
                                            Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(PasswordResetActivity.this,
                                            "Failed to send reset email", Toast.LENGTH_SHORT).show();
                                }
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }

}