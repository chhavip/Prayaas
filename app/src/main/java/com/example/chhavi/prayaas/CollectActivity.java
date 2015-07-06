package com.example.chhavi.prayaas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.AppConfig;
import app.AppController;
import helper.CollectionCenterAdapter;
import helper.GsonRequest;
import models.Centers;
import models.CollectionCenterResponse;
import models.Events;


public class CollectActivity extends ActionBarActivity {
    List<CollectionCenterResponse.CollectionCenter> centers;
    List<Centers> allCenters;

    RecyclerView mRecyclerView;
    CollectionCenterResponse.CollectionCenter center;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    String url;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewCollect);
        activity = this;
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        allCenters = new ArrayList<>();

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CollectionCenterAdapter(allCenters, R.layout.collection_center, this);
        mRecyclerView.setAdapter(mAdapter);
        url = AppConfig.BASE_URL + "fetch_collect_centres.php";

//        RequestQueue queue = AppController.getInstance().getRequestQueue();
//
//        GsonRequest<String> myreq = new GsonRequest<>(Request.Method.POST, url, CollectionCenterResponse.class, createMyReqSuccessListener(),
//                createMyReqErrorListener());
//
//        queue.add(myreq);
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //PD.dismiss();
                        //   item_et.setText("");
                        Log.e("response user", response);
                        Toast.makeText(activity,
                                "Centers fetched",
                                Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("centres");
                            for(int i = 0; i< jsonArray.length(); i++)  {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                allCenters.add(new Centers(jsonObject1.getString("name"), jsonObject1.getString("address"),
                                        jsonObject1.getString("contact"), jsonObject1.getString("days_active"),
                                        jsonObject1.getString("timings"), jsonObject1.getString("collection_status")));

                            }
                            mAdapter.notifyDataSetChanged();
                            Log.i("test", response);
                         //    = jsonObject.get("reward").toString();
                        } catch (JSONException e) {
                            Toast.makeText(activity,
                                    "failed to fetch amount",
                                    Toast.LENGTH_SHORT).show();
                        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collect, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void callCenter(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

//    private Response.Listener<CollectionCenterResponse> createMyReqSuccessListener() {
//        return new Response.Listener<CollectionCenterResponse>() {
//            @Override
//            public void onResponse(CollectionCenterResponse response) {
//                // Do whatever you want to do with response;
//                // Like response.tags.getListing_count(); etc. etc.
//                centers = new ArrayList<CollectionCenterResponse.CollectionCenter>();
//                centers = response.getCollectionCenters();
//                initializeData();
//                initializeAdapter();
//                Log.e("responseCollect", response.toString());
//
//            }
//        };
//    }
//
//    private Response.ErrorListener createMyReqErrorListener() {
//        return new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // Do whatever you want to do with error.getMessage();
//            }
//        };
//    }

    private void initializeData() {
        //  events = new ArrayList<>();

    }


     /*   events.add(new Events("Event 1", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Event 2", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Event 3", "7/06/2015","Central Park", R.drawable.nepalim));
        events.add(new Events("Event 4", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Emma Wilson", "7/06/2015","Central Park", R.drawable.nepalim));*/


    private void initializeAdapter(){




        // specify an adapter (see also next example)


    }
}
