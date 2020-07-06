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
public class DestinationFrag extends Fragment {
    private View view;

    public DestinationFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_destination, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView destinationsList = view.findViewById(R.id.destination_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        destinationsList.setLayoutManager(layoutManager);
        destinationsList.setHasFixedSize(true);
        RecyclerView.Adapter adapter = new DestinationAdapter(this.getActivity(),DestinationDescriptionClass.listOfAvailableDestinations);
        destinationsList.setAdapter(adapter);

    }
}
