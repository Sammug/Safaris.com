package com.example.etour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SafarisViewActivity extends AppCompatActivity {
    Button btnReserveBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safaris_view);
        //
        btnReserveBooking = findViewById(R.id.btnReserveBookin);
        //set onClick Listener
        btnReserveBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SafarisViewActivity.this, CheckOutActivity.class));
            }
        });
    }
}