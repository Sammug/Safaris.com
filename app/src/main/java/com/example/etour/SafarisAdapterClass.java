package com.example.etour;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class SafarisAdapterClass extends FirestoreRecyclerAdapter<SafarisClass, SafarisAdapterClass.ViewHolder> {
     Context mContext;

    public SafarisAdapterClass(@NonNull FirestoreRecyclerOptions<SafarisClass> options, Context context) {
        super(options);
        mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull final SafarisClass safarisClass) {
        viewHolder.tvSafarisName.setText(safarisClass.getName());
        viewHolder.tvSafariDescription.setText(safarisClass.getDescription());

        Picasso.get()
                .load(safarisClass.getSafarisImageUri())
                .fit()
                .into(viewHolder.safariView);

        viewHolder.safariView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,SafarisViewActivity.class);
                intent.putExtra("name", safarisClass.getName());
                intent.putExtra("description", safarisClass.getDescription());
                intent.putExtra("safarisImageUri", safarisClass.getSafarisImageUri());
                mContext.startActivity(intent);
            }
        });


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
