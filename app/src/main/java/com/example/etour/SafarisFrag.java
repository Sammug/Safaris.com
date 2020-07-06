package com.example.etour;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SafarisFrag extends Fragment {
    public View view;

    public SafarisFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_safaris, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView safarisView = view.findViewById(R.id.safarisList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        RecyclerView.Adapter myAdapter = new safarisAdapterClass(this.getActivity(),AvailableSafaris.availableSafaris);
        safarisView.setLayoutManager(layoutManager);
        safarisView.setAdapter(myAdapter);
    }
}
