package com.example.etour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalService;

public class CheckOutActivity extends AppCompatActivity {
    TextView tvVisa, tvPayPal, tvMpesa;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        manager = getSupportFragmentManager();

        tvVisa = findViewById(R.id.tvVisa);
        tvPayPal = findViewById(R.id.tvPayPal);
        tvMpesa = findViewById(R.id.tvMpesa);


        tvVisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .replace(R.id.fragment_holder,new PayWithVisaFrag())
                        .addToBackStack(null)
                        .commit();
            }
        });

        tvPayPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .replace(R.id.fragment_holder,new PayPalFrag())
                        .addToBackStack(null)
                        .commit();
            }
        });

        tvMpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .replace(R.id.fragment_holder,new MpesaFrag())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}