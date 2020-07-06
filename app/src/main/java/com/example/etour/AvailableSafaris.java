package com.example.etour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.ArrayList;

public class AvailableSafaris extends AppCompatActivity {
    Fragment safarisFrag;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public static ArrayList<SafarisClass> availableSafaris;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_safaris);

        availableSafaris = new ArrayList<>();
        availableSafaris.add(new SafarisClass("Mombasa","Pwani ya kenya have got several resorts"));
        availableSafaris.add(new SafarisClass("Kericho", "Kericho is in Rift Vallet region of kenya"));

        fragmentManager = getSupportFragmentManager();
        safarisFrag = fragmentManager.findFragmentById(R.id.safarisList);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();
    }
}
