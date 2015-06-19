package com.example.chhavi.prayaas;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

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
 * Use the {@link AllEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllEventsFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllEventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllEventsFragment newInstance(String param1, String param2) {
        AllEventsFragment fragment = new AllEventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AllEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private List<Events> events;
    private RecyclerView rv;
    String url;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_events, container, false);
        events = new ArrayList<>();
        url = "http://192.168.0.102/android_login_api/fetch_events.php";
        RequestQueue queue = AppController.getInstance().getRequestQueue();

        GsonRequest<EventResponse> myreq = new GsonRequest<EventResponse>(Request.Method.GET,url,EventResponse.class, createMyReqSuccessListener(),
                createMyReqErrorListener());

        queue.add(myreq);
        rv=(RecyclerView)v.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
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
                List<EventResponse.EventModel> events = new ArrayList<EventResponse.EventModel>();
                events = response.getEvents();
                Log.e("response", events.get(0).getName());

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


        events.add(new Events("Event 1", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Event 2", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Event 3", "7/06/2015","Central Park", R.drawable.nepalim));
        events.add(new Events("Event 4", "7/06/2015","Central Park", R.drawable.logo));
        events.add(new Events("Emma Wilson", "7/06/2015","Central Park", R.drawable.nepalim));
    }

    private void initializeAdapter(){
        EventCardAdapter adapter;
        adapter = new EventCardAdapter(events, R.layout.event_card_item);


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
