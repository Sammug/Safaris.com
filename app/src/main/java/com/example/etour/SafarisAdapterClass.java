package com.example.etour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SafarisAdapterClass extends FirestoreRecyclerAdapter<SafarisClass, SafarisAdapterClass.ViewHolder> {

    public SafarisAdapterClass(@NonNull FirestoreRecyclerOptions<SafarisClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull SafarisClass safarisClass) {
        viewHolder.tvSafarisName.setText(safarisClass.getSafarisName());
        viewHolder.tvSafariDescription.setText(safarisClass.getSafarisDescription());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView safariView;
        TextView tvSafarisName;
        TextView tvSafariDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            safariView = itemView.findViewById(R.id.safarisView);
            tvSafarisName = itemView.findViewById(R.id.safarisName);
            tvSafariDescription = itemView.findViewById(R.id.safarisDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @NonNull
    @Override
    public SafarisAdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View safarisView = LayoutInflater.from(parent.getContext()).inflate(R.layout.safaris_layout, parent, false);
        return new ViewHolder(safarisView);
    }

}