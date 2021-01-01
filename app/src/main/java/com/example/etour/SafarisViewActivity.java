package com.example.etour;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class SafarisViewActivity extends AppCompatActivity {
    private static final String TAG = "SafarisActivity";
    String safarisName,safarisDescription, safarisImage;
    String destination_Name, destination_Location, destination_Rating, destination_Price, destination_Uri;
    TextView tv_Name, tv_Description, tv_Rating, tv_Price;
    ImageView iv_SafarisPhoto;
    Button btnReserveBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safaris_view);
        //
        iv_SafarisPhoto = findViewById(R.id.ivSafarisPhoto);
        tv_Name = findViewById(R.id.tvNameOfDestination);
        tv_Description = findViewById(R.id.tvDescription);
        tv_Rating = findViewById(R.id.tvRate);
        tv_Price = findViewById(R.id.tvPackagePrice);

        getIncomingIntents();
        receiveIntents();
        btnReserveBooking = findViewById(R.id.btnReserveBookin);
        //set onClick Listener
        btnReserveBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SafarisViewActivity.this, CheckOutActivity.class));
            }
        });
    }

    private void receiveIntents() {
        Log.d(TAG, "receiveIntents: receiving intents from destination adapter");
        if (getIntent().hasExtra("destinationName")
                && getIntent().hasExtra("destinationLocation")
                && getIntent().hasExtra("destinationRating")
                && getIntent().hasExtra("destinationPrice")
                && getIntent().hasExtra("destinationImageUri")){
            destination_Name = getIntent().getStringExtra("destinationName");
            destination_Location = getIntent().getStringExtra("destinationLocation");
            destination_Rating = getIntent().getStringExtra("destinationRating");
            destination_Price = getIntent().getStringExtra("destinationPrice");
            destination_Uri = getIntent().getStringExtra("destinationImageUri");

            setDestination(destination_Name, destination_Location, destination_Rating, destination_Price, destination_Uri);

        }
    }

    private void setDestination(String destination_name, String destination_location, String destination_rating, String destination_price, String destination_uri) {
        Log.d(TAG, "setDestination: setting views from intents");
        tv_Name.setText(destination_name);
        tv_Description.setText(destination_location);
        tv_Rating.setText(destination_rating);
        tv_Price.setText(destination_price);
        Picasso.get()
                .load(destination_uri)
                .fit()
                .into(iv_SafarisPhoto);
    }

    private void getIncomingIntents() {
        Log.d(TAG, "getIncomingIntents: getting extra string intents");
        if (getIntent().hasExtra("name")
                && getIntent().hasExtra("description")
                && getIntent().hasExtra("safarisImageUri")){
            safarisName = getIntent().getStringExtra("name");
            safarisDescription = getIntent().getStringExtra("description");
            safarisImage = getIntent().getStringExtra("safarisImageUri");

            setView(safarisName, safarisDescription, safarisImage);
        }

    }

    private void setView(String safarisName, String safarisDescription, String safarisImage) {
        Log.d(TAG, "setView: setting image and text views from intent");
        tv_Name.setText(safarisName);
        tv_Description.setText(safarisDescription);
        Picasso.get().
                load(safarisImage)
                .fit()
                .into(iv_SafarisPhoto);
    }
}