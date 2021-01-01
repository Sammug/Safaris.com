package com.example.etour;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class DestinationFrag extends Fragment{
    //List<AvailableDestinationsList> destinations;
    private View view;
    DestinationAdapterClass myAdapter;
    RecyclerView destinationsList;
    FirebaseFirestore firestore;
    Query myQuery;

    public DestinationFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_destination, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //destinations = new ArrayList<>();
        setAdapter();
    }

    private void setAdapter() {
         firestore = FirebaseFirestore.getInstance();
        CollectionReference destinationRef = firestore.collection("DESTINATIONS");
        myQuery = destinationRef.orderBy("destinationName", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<AvailableDestinationsList> options = new FirestoreRecyclerOptions
                .Builder<AvailableDestinationsList>()
                .setQuery(myQuery,AvailableDestinationsList.class)
                .build();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        destinationsList = view.findViewById(R.id.destination_list);
        destinationsList.setLayoutManager(layoutManager);
        destinationsList.setHasFixedSize(true);
        Context context = getContext();
        myAdapter = new DestinationAdapterClass(options, context);////////
        destinationsList.setAdapter(myAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (myAdapter != null){
            myAdapter.stopListening();
        }
    }
}
