package com.example.etour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CheckOutActivity extends AppCompatActivity {
    TextView tvVisa, tvPayPal, tvMpesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        tvVisa = findViewById(R.id.tvVisa);
        tvPayPal = findViewById(R.id.tvPayPal);
        tvMpesa = findViewById(R.id.tvMpesa);

        tvVisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvPayPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvMpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}