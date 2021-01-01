package com.example.etour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BookingStatusActivity extends AppCompatActivity {
    TextView tvMyOrders, tvCancelledOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_satatus);
        tvMyOrders = findViewById(R.id.tvMyBookings);
        tvCancelledOrders= findViewById(R.id.tvCancelledBookings);

        tvMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvCancelledOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}