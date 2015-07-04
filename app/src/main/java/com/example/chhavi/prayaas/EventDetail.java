package com.example.chhavi.prayaas;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.gc.materialdesign.views.ButtonRectangle;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.gson.Gson;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

import app.AppConfig;
import helper.SQLiteHandler;
import models.EventResponse;
import models.Events;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gc.materialdesign.views.ButtonRectangle;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.AppController;
import models.SingleEventRespnse;


/**
 * Created by chhavi on 8/6/15.
 */

public class EventDetail extends AppCompatActivity implements ObservableScrollViewCallbacks, View.OnClickListener {


    private ImageView mImageView;
    private Toolbar mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;
    ButtonRectangle going;
    ButtonRectangle notSure;
    Events events;
    String url;
    boolean removeGoing;
    int eventId = 1;
    String fetcheventURl;
    TextView description;
    TextView date;
    TextView time;
    TextView venue;
    TextView contact;
    TextView organisation;
    TextView duration;
    HashMap<String,String> userInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_scroll_event_details);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        eventId = b.getInt("selectedEvent");

        //
        // Events events = (Events) b.get("selectedEvent");

        fetcheventURl = AppConfig.BASE_URL + "fetch_single_event.php";
        url = AppConfig.BASE_URL+ "store_user_event.php";

        fetchEvent();
        mImageView = (ImageView) findViewById(R.id.image);
        description = (TextView)findViewById(R.id.body);
        date = (TextView)findViewById(R.id.date_text);
        time = (TextView)findViewById(R.id.time_text);
        venue = (TextView)findViewById(R.id.venue_text);
        contact = (TextView)findViewById(R.id.eventContact);
        organisation = (TextView)findViewById(R.id.event_organisation);
        duration = (TextView)findViewById(R.id.eventDuration);

        mImageView.setImageResource(R.drawable.nepalim);
        mToolbarView = (Toolbar) findViewById(R.id.toolbar);
        mToolbarView.setTitle("Title");
        mToolbarView.setBackgroundColor(
                ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.white)));
        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);
        going = (ButtonRectangle) findViewById(R.id.goingButton);
       // notSure = (ButtonRectangle) findViewById(R.id.notSureButton);

        mParallaxImageHeight = getResources().getDimensionPixelSize(
                R.dimen.parallax_image_height);
        SQLiteHandler handler = new SQLiteHandler(this);
        userInfo = handler.getUserDetails();
        // SQLiteDatabase db = handler.getReadableDatabase();


        //give url


        //get actual event and user ids.
        going.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = userInfo.get("uid");
                Log.e("message",userId +"  " + eventId);
                String status ="going";
                UpdateUserEvent(userId,eventId+"",status);

            }
        });

    /*    notSure.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String userId = userInfo.get("uid");
                String status ="not sure";
                UpdateUserEvent(userId,eventId+"",status);
            }
        });*/
    }


    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        ViewHelper.setTranslationY(mImageView, scrollY / 2);

        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));

    }
    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goingButton) {
            //Update in user event database. Add a tuple with event name and user name and going.
            Intent i = new Intent();
            i.putExtra("selectedEvent", events);
            setResult(1, i);
            finish();
        } else {
            Intent i = new Intent();
            i.putExtra("selectedEvent", events);
            setResult(2, i);
            finish();
            //Update in user event database. Add a tuple with event name and user name and not sure.

        }
    }






    void UpdateUserEvent(final String userId, final String eventId, final String status){

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //PD.dismiss();
                        //   item_et.setText("");
                        Log.e("response user", response);
                        Toast.makeText(getApplicationContext(),
                                "Data Inserted Successfully",
                                Toast.LENGTH_SHORT).show();



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // PD.dismiss();
                Log.e("error", error.toString());
                Toast.makeText(getApplicationContext(),
                        "failed to insert", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userId", userId);
                params.put("eventId",eventId);
                params.put("status",status);

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

    public void fetchEvent(){


        StringRequest postRequest = new StringRequest(Request.Method.POST, fetcheventURl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //PD.dismiss();
                        //   item_et.setText("");
                        Log.e("response user", response);
                        Toast.makeText(getApplicationContext(),
                                "Data Inserted Successfully",
                                Toast.LENGTH_SHORT).show();

                        SingleEventRespnse eventG = new Gson().fromJson(response,SingleEventRespnse.class);
                        Log.e("test", eventG.getEvent().getName());
                        EventResponse.EventModel event =eventG.getEvent();
                        description.setText(event.getDescription());
                        time.setText(event.getTime());
                        date.setText(event.getDate());
                        venue.setText(event.getVenue());
                        contact.setText(event.getContact());
                        organisation.setText(event.getOrganisation());
                        duration.setText(event.getDuration());


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // PD.dismiss();
                Log.e("error",error.toString());
                Toast.makeText(getApplicationContext(),
                        "failed to insert", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("eventId",eventId+"");


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


