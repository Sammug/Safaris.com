package com.example.etour;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Fragment fragmentDestination;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    RecyclerView myRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentDestination = fragmentManager.findFragmentById(R.id.destination_frag);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();
        //


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationBottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //................
        final FloatingActionButton floatingActionButton = findViewById(R.id.fabSearch);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AvailableSafarisActivity.class));
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
            case R.id.action_home:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return  true;
            case R.id.action_bookings:
                startActivity(new Intent(MainActivity.this, BookingStatusActivity.class));
                return true;
            case  R.id.action_profile:
                //fragment = new ProfileFragment();
                //loadFragment(fragment);
                startActivity(new Intent(MainActivity.this, UserProfile.class));
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

    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        MenuItem searchDestination = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchDestination.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        return true;
    }**/
}