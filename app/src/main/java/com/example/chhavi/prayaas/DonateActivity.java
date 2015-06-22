package com.example.chhavi.prayaas;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

/**
 * Created by chhavi on 8/6/15.
 */
public class DonateActivity extends ActionBarActivity implements View.OnClickListener {

    EditText donationAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate_layout);
        donationAmount = (EditText) findViewById(R.id.donationAmount);

        ButtonRectangle donate = (ButtonRectangle) findViewById(R.id.donateButton);

        donate.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        SharedPreferences sp = getSharedPreferences("Prayaas", Context.MODE_PRIVATE);
        int maxAmount = Integer.parseInt(sp.getString(PrayaasContract.USER_TABLE_WALLET, null));
        int enteredAmount = Integer.parseInt(donationAmount.getText().toString());
        if(enteredAmount>maxAmount) {
            Toast.makeText(this, "Insufficient balance", Toast.LENGTH_LONG).show();
        }
        else    {
            //TODO whatever has to be done with donate
        }
    }
}
