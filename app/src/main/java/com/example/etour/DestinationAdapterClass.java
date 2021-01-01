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

public class DestinationAdapterClass extends FirestoreRecyclerAdapter<AvailableDestinationsList, DestinationAdapterClass.ViewholderClass> {
Context m_Context;
    public DestinationAdapterClass(@NonNull FirestoreRecyclerOptions<AvailableDestinationsList> options, Context context) {
        super(options);
        m_Context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewholderClass viewholderClass, int i, @NonNull final AvailableDestinationsList availableDestinationsList) {

        viewholderClass.tvDestinationName.setText(availableDestinationsList.getDestinationName());
        viewholderClass.tvDestinationLocation.setText(availableDestinationsList.getDestinationLocation());
        viewholderClass.tvRating.setText(availableDestinationsList.getDestinationRating());
        viewholderClass.tvDestinationPrice.setText(availableDestinationsList.getDestinationPrice());
        Picasso.get()
                .load(availableDestinationsList.getDestinationImageUri())
                .fit()
                .into(viewholderClass.destinationPicture);
        viewholderClass.destinationPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_Context,SafarisViewActivity.class);
                intent.putExtra("destinationName", availableDestinationsList.getDestinationName());
                intent.putExtra("destinationLocation", availableDestinationsList.getDestinationLocation());
                intent.putExtra("destinationRating",availableDestinationsList.getDestinationRating());
                intent.putExtra("destinationPrice",availableDestinationsList.getDestinationPrice());
                intent.putExtra("destinationImageUri",availableDestinationsList.getDestinationImageUri());
                m_Context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ViewholderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewholderClass(view);
    }

    public static class ViewholderClass extends RecyclerView.ViewHolder{
        ImageView destinationPicture;
        TextView tvDestinationName;
        TextView tvDestinationLocation;
        TextView tvRating;
        TextView tvDestinationPrice;

        public ViewholderClass(@NonNull View itemView) {
            super(itemView);
            destinationPicture = itemView.findViewById(R.id.destinationPicRepresentation);
            tvDestinationName = itemView.findViewById(R.id.destinationName);
            tvDestinationLocation = itemView.findViewById(R.id.destinationLocation);
            tvRating = itemView.findViewById(R.id.rating);
            tvDestinationPrice = itemView.findViewById(R.id.package_price);
        }
    }

}
