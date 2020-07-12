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

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

    private ArrayList<AvailableDestinationsList> listOfAvailableDestinations;

    DestinationAdapter(Context context, ArrayList<AvailableDestinationsList> destinationList){
        listOfAvailableDestinations = destinationList;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView destinationPicture;
        TextView tvDestinationName;
        TextView tvDestinationLocation;
        TextView tvRating;
        TextView tvDestinationPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            destinationPicture = itemView.findViewById(R.id.destinationPicRepresentation);
            tvDestinationName = itemView.findViewById(R.id.destinationName);
            tvDestinationLocation = itemView.findViewById(R.id.destinationLocation);
            tvRating = itemView.findViewById(R.id.rating);
            tvDestinationPrice = itemView.findViewById(R.id.package_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }
    }
    @NonNull
    @Override
    public DestinationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(listOfAvailableDestinations.get(position));
        holder.tvDestinationName.setText(listOfAvailableDestinations.get(position).getDestinationName());
        holder.tvDestinationLocation.setText(listOfAvailableDestinations.get(position).getDestinationLocation());
        holder.tvRating.setText(listOfAvailableDestinations.get(position).getDestinationRating());
        holder.tvDestinationPrice.setText(listOfAvailableDestinations.get(position).getDestinationPrice());
    }

    @Override
    public int getItemCount() {
        return listOfAvailableDestinations.size();
    }
}
