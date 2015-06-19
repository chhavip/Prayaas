package com.example.chhavi.prayaas;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.AppController;
import helper.EventCardAdapter;
import helper.GsonRequest;
import models.EventResponse;
import models.Events;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsFragment extends android.support.v4.app.Fragment {


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

        rv=(RecyclerView)v.findViewById(R.id.rv_going);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
        initializeData();
        initializeAdapter();
        return v;
    }
    private void initializeData(){
        events = new ArrayList<>();


        events.add(new Events("Event 1", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Event 2", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Event 3", "7/06/2015","Central Park", R.drawable.nepalim));
        events.add(new Events("Event 4", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Emma Wilson", "7/06/2015","Central Park", R.drawable.nepalim));
    }

    private void initializeAdapter(){
        EventCardAdapter adapter;
        adapter = new EventCardAdapter(events, R.layout.fragment_going_event);


        adapter.SetOnItemClickListener(new EventCardAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                // do something with position

                Intent i = new Intent(getActivity(),EventDetail.class);
                Events selectedEvent = events.get(position);
                i.putExtra("selectedEvent", (Serializable) selectedEvent);
                startActivityForResult(i, 1);
            }
        });
        rv.setAdapter(adapter);


    }


}
