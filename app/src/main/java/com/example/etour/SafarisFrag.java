package com.example.etour;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class SafarisFrag extends Fragment {
    public View view;
    private SafarisAdapterClass myAdapter;
    FirebaseFirestore firebaseFirestore;
    Query query;

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
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        firebaseFirestore = FirebaseFirestore.getInstance();
       CollectionReference safarisRef = firebaseFirestore.collection("SAFARIS");
        //query = firebaseFirestore.collection("SAFARIS");
       query = safarisRef.orderBy("name", Query.Direction.ASCENDING);
                //.orderBy("name",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<SafarisClass> options = new FirestoreRecyclerOptions.Builder<SafarisClass>()
                .setQuery(query, SafarisClass.class)
                .build();
        RecyclerView safarisView = view.findViewById(R.id.safarisList);
        Context mContext = getContext();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        myAdapter = new SafarisAdapterClass(options, mContext);
        safarisView.setLayoutManager(layoutManager);
        safarisView.setAdapter(myAdapter);
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
