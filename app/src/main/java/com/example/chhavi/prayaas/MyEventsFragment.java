package com.example.chhavi.prayaas;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.AppConfig;
import app.AppController;
import helper.EventCardAdapter;
import helper.GsonRequest;
import helper.SQLiteHandler;
import models.EventResponse;
import models.Events;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsFragment extends android.support.v4.app.Fragment {

    HashMap<String,String> userInfo;
    List<EventResponse.EventModel> eventsall;
    String userId;
    public MyEventsFragment() {
        // Required empty public constructor
    }
    private List<Events> events;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_events, container, false);
        events = new ArrayList<>();
        SQLiteHandler handler = new SQLiteHandler(getActivity());
        userInfo = handler.getUserDetails();
        userId = userInfo.get("uid");


        String url = AppConfig.BASE_URL + "fetch_user_events.php/?userid="+userId;
        RequestQueue queue = AppController.getInstance().getRequestQueue();

        GsonRequest<EventResponse> myreq = new GsonRequest<EventResponse>(Request.Method.GET,url,EventResponse.class, createMyReqSuccessListener(),
                createMyReqErrorListener());

        queue.add(myreq);


        rv=(RecyclerView)v.findViewById(R.id.rv_going);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
      //  fetchmyEvents();
        initializeData();
        initializeAdapter();
        return v;
    }
    private Response.Listener<EventResponse> createMyReqSuccessListener() {
        return new Response.Listener<EventResponse>() {
            @Override
            public void onResponse(EventResponse response) {
                // Do whatever you want to do with response;
                // Like response.tags.getListing_count(); etc. etc.
                 eventsall = new ArrayList<EventResponse.EventModel>();

                eventsall = response.getEvents();
                initializeData();
                initializeAdapter();
                Log.e("response", "something");

            }
        };
    }
    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Do whatever you want to do with error.getMessage();
            }
        };
    }

    private void initializeData(){
        events = new ArrayList<>();
        if(eventsall!=null) {
            for (int i = 0; i < eventsall.size(); i++) {

                events.add(new Events(eventsall.get(i).getName(), eventsall.get(i).getDate(), eventsall.get(i).getVenue(), R.drawable.nepalim,eventsall.get(i).getEid()));

            }
        }

   /*     events.add(new Events("Event 1", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Event 2", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Event 3", "7/06/2015","Central Park", R.drawable.nepalim));
        events.add(new Events("Event 4", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Emma Wilson", "7/06/2015","Central Park", R.drawable.nepalim));*/
    }

    private void initializeAdapter(){
        EventCardAdapter adapter;
        adapter = new EventCardAdapter(events, R.layout.fragment_going_event,getActivity());


        adapter.SetOnItemClickListener(new EventCardAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                // do something with position

                Intent i = new Intent(getActivity(),EventDetail.class);
           //     EventResponse.EventModel selectedEvent = eventsall.get(position);
                i.putExtra("selectedEvent", events.get(position).id);
                startActivityForResult(i, 1);
            }
        });
        rv.setAdapter(adapter);


    }


}
