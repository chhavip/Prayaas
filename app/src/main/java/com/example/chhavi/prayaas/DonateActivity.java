package com.example.chhavi.prayaas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.AppConfig;
import app.AppController;
import helper.GsonRequest;
import helper.SQLiteHandler;
import models.EventResponse;

/**
 * Created by chhavi on 8/6/15.
 */
public class DonateActivity extends ActionBarActivity implements View.OnClickListener {
    Activity activity;
    EditText donationAmount;
    HashMap<String,String> userInfo;
    String userId;
    String wallet;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate_layout);
        Intent i = getIntent();
        activity = this;
        SQLiteHandler handler = new SQLiteHandler(this);
        userInfo = handler.getUserDetails();
        userId = userInfo.get("uid");
        //change to relevant wallet file
        url = AppConfig.BASE_URL + "donate.php";
        wallet = i.getExtras().get("Wallet").toString();
        donationAmount = (EditText) findViewById(R.id.donationAmount);
        ButtonRectangle b = (ButtonRectangle) findViewById(R.id.donateButton);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int wall = Integer.parseInt(wallet);

        int don = Integer.valueOf(donationAmount.getText().toString());
        if(don > wall)    {
            Toast.makeText(this, "Your rewards are less than what you wish to donate!", Toast.LENGTH_SHORT).show();
            return;
        }
        else    {
            //do what you have to with the entered amount
            fetchEvent();



        }
    }
    public void fetchEvent(){

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //PD.dismiss();
                        //   item_et.setText("");
                        Log.e("response user", response);
                        Toast.makeText(activity,
                                "Data Inserted Successfully",
                                Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.i("test", response);
//                            wallet = jsonObject.get("reward").toString();
//                            incentive_value.setText(wallet);
                        } catch (JSONException e) {
                            Toast.makeText(activity,
                                    "failed",
                                    Toast.LENGTH_SHORT).show();
                        }

//                        WalletResponse response1 = new Gson().fromJson(response,WalletResponse.class);
//                        Log.e("test", response1.getWallet());
//                        WalletResponse finalResponse = response1;
//                        incentive_value.setText(finalResponse.getWallet());
//                        wallet = finalResponse.getWallet();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // PD.dismiss();
                Log.e("error",error.toString());
                Toast.makeText(activity,
                        "failed to insert", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("userid",userId+"");
                params.put("amount",donationAmount.getText().toString()+"");


                return params;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(postRequest);

    }
}
