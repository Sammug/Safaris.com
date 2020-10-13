package com.example.etour;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.model.AccessToken;

public class MpesaFrag extends Fragment {
    Daraja daraja;
    View view;
    EditText editTextPhoneNumber;
    Button sendButton;

    public MpesaFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mpesa, container, false);
        editTextPhoneNumber = view.findViewById(R.id.etPhoneNumber);
        sendButton = view.findViewById(R.id.btnSend);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });
        return view;
    }

    private void processPayment() {
        daraja = Daraja.with("eGlROyfr6RvQp90ouaVUG3dw5HjBxwpj", "gmKec0u3jrAtkwWQ", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Log.i(MpesaFrag.this.getClass().getSimpleName(), accessToken.getAccess_token());
            }

            @Override
            public void onError(String error) {
                Log.e(MpesaFrag.this.getClass().getSimpleName(), error);
            }
        });
    }
}