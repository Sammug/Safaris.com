package com.example.etour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.Objects;

public class PayPalFrag extends Fragment {
    private EditText Amount;
    Button btnPayPal;
    View view;

    private static final int PAYPAL_REQUEST_CODE = 123;
    public static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);


    public PayPalFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //btnPayPal = btnPayPal.findViewById(R.id.btnPayPal);

        //start paypal service
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pay_pal, container, false);
        Amount = view.findViewById(R.id.et_paymentAmount);
        btnPayPal = view.findViewById(R.id.btnPayPal);
        btnPayPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPayment();
            }
        });
        Intent payPalIntent = new Intent(getActivity(),PayPalService.class);
        payPalIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        Objects.requireNonNull(getActivity()).startService(payPalIntent);
        return view;


    }

    private void getPayment() {
        String paymentAmount = Amount.getText().toString();
        //paypal payment
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)),
                "USD", "Package fee", PayPalPayment.PAYMENT_INTENT_SALE);
        // paypal payment activity intent
        Intent paymentIntent = new Intent(getActivity(), PaymentActivity.class);
        paymentIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        // put Payment to the intent
        paymentIntent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        Objects.requireNonNull(getActivity()).startActivityForResult(paymentIntent, PAYPAL_REQUEST_CODE);
    }

    /**
     * Receive the result from a previous call to
     * {@link #startActivityForResult(Intent, int)}.  This follows the
     * related Activity API as described there in
     * .
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PAYPAL_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                //Payment confirmation
                assert data != null;
                PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (paymentConfirmation != null){
                    try {
                        String paymentDetails = paymentConfirmation.toJSONObject().toString(4);
                        Log.i("Package Payment", paymentDetails);
                        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "Payment details from paypal",Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        Log.i("paymentExample ", "an unknown error occurred while processing your payment", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED){
                Log.i("paymentExample", "Result cancelled");
            }
            else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID){
                Log.i("paymentExample", "An invalid payment was submitted");
            }
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        Objects.requireNonNull(getActivity()).stopService(new Intent(getActivity(),PayPalService.class));
        super.onDestroy();
    }
}