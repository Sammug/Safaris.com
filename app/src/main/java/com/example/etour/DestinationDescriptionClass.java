package com.example.etour;

import android.app.Application;
import android.content.ClipData;

import java.util.ArrayList;

public class DestinationDescriptionClass extends Application {
    public static  ArrayList<AvailableDestinationsList> listOfAvailableDestinations;
    @Override
    public void onCreate() {
        super.onCreate();
        listOfAvailableDestinations = new ArrayList<>();
        listOfAvailableDestinations.add(new AvailableDestinationsList("Mombasa",
                "Nyali","80/100","$120"));
        listOfAvailableDestinations.add(new AvailableDestinationsList("Mombasa",
                "Nyali","80/100","$120"));
        listOfAvailableDestinations.add(new AvailableDestinationsList("Mombasa",
                "Nyali","80/100","$120"));
        listOfAvailableDestinations.add(new AvailableDestinationsList("Mombasa",
                "Nyali","80/100","$120"));
        listOfAvailableDestinations.add(new AvailableDestinationsList("Mombasa",
                "Nyali","80/100","$120"));
        listOfAvailableDestinations.add(new AvailableDestinationsList("Mombasa",
                "Nyali","80/100","$120"));
        listOfAvailableDestinations.add(new AvailableDestinationsList("Mombasa",
                "Nyali","80/100","$120"));
    }
}
