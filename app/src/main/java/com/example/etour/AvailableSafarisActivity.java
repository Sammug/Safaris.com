package com.example.etour;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AvailableSafarisActivity extends AppCompatActivity {
    Fragment safarisFrag;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    //public static ArrayList<SafarisClass> availableSafaris;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_safaris);

        fragmentManager = getSupportFragmentManager();
        safarisFrag = fragmentManager.findFragmentById(R.id.safaris_Frag);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();
    }
}
