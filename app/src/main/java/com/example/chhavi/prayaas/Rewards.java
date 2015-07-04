package com.example.chhavi.prayaas;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gc.materialdesign.views.ButtonRectangle;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.AppConfig;
import app.AppController;
import helper.GsonRequest;
import helper.SQLiteHandler;
import models.EventResponse;
import models.SingleEventRespnse;
import models.UserResponse;
import models.WalletResponse;

/**
 * Created by chhavi on 8/6/15.
 */
public class Rewards extends android.support.v4.app.Fragment {
    ButtonRectangle donateButton;
    ButtonRectangle collectButton;
    HashMap<String,String> userInfo;
    String userId;
    String wallet;
    String url;
    TextView incentive_value;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_rewards,null);

        SQLiteHandler handler = new SQLiteHandler(getActivity());
        userInfo = handler.getUserDetails();
        userId = userInfo.get("uid");
        incentive_value = (TextView) v.findViewById(R.id.incentive_value);
        //change to relevant wallet file
        url = AppConfig.BASE_URL + "fetch_user_wallet.php/?userid="+userId;
        fetchEvent();



        donateButton = (ButtonRectangle)v.findViewById(R.id.donate_button);
        collectButton = (ButtonRectangle)v.findViewById(R.id.collect_button);

        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),DonateActivity.class);
                i.putExtra("Wallet", wallet);
                startActivityForResult(i, 1);
            }
        });
        collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CollectActivity.class);
                startActivity(i);
            }
        });


        return v;
    }

    public void fetchEvent(){

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //PD.dismiss();
                        //   item_et.setText("");
                        Log.e("response user", response);
                        Toast.makeText(getActivity(),
                                "Data Inserted Successfully",
                                Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.i("test", response);
                            wallet = jsonObject.get("reward").toString();
                            incentive_value.setText(wallet);
                        } catch (JSONException e) {
                            Toast.makeText(getActivity(),
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
                Toast.makeText(getActivity(),
                        "failed to insert", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("userId",userId+"");


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
