package com.example.etour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class safarisAdapterClass extends RecyclerView.Adapter<safarisAdapterClass.ViewHolder> {
    public ArrayList<SafarisClass> availableSafaris;

    public safarisAdapterClass(Context context, ArrayList<SafarisClass> availableSafarisList) {
        availableSafaris = availableSafarisList;
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
    public safarisAdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View safarisView = LayoutInflater.from(parent.getContext()).inflate(R.layout.safaris_layout, parent,false);
        return new ViewHolder(safarisView);
    }

    @Override
    public void onBindViewHolder(@NonNull safarisAdapterClass.ViewHolder holder, int position) {
        holder.itemView.setTag(availableSafaris.get(position));
        holder.tvSafarisName.setText(availableSafaris.get(position).getSafarisName());
        holder.tvSafariDescription.setText(availableSafaris.get(position).getSafarisDescription());

    }

    @Override
    public int getItemCount() {
        return availableSafaris.size();
    }
}
