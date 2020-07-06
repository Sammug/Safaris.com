package com.example.etour;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Button btnLogin;
    Fragment fragmentDestination;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    RecyclerView myRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentDestination = fragmentManager.findFragmentById(R.id.destination_list);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnLogin) {
                    startActivity(new Intent(MainActivity.this, SignIn.class));
                } else {
                    throw new IllegalStateException("Unexpected value: " + v.getId());
                }

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationBottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //................
        final FloatingActionButton floatingActionButton = findViewById(R.id.fabSearch);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AvailableSafaris.class));
            }
        });
        myRecyclerView = findViewById(R.id.destination_list);
        myRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy<0){
                    floatingActionButton.hide();
                }
                else if(dy>0)
                {
                    floatingActionButton.show();
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment;

        switch (menuItem.getItemId()){
            case R.id.action_search:
                fragment = new SearchFragment();
                loadFragment(fragment);
                return  true;
            case R.id.action_bookings:
                fragment = new BookingsFragment();
                loadFragment(fragment);
                return true;
            case  R.id.action_profile:
                fragment = new ProfileFragment();
                loadFragment(fragment);
                return  true;
        }
        return false;
    }
    private void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragments_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}