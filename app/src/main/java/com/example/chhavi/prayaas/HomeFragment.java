package com.example.chhavi.prayaas;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import app.AppController;
import helper.EventCardAdapter;
import helper.GsonRequest;
import models.EventResponse;
import models.Events;

import static com.example.chhavi.prayaas.R.color.abc_primary_text_disable_only_material_dark;
import static com.example.chhavi.prayaas.R.color.white;
import static com.example.chhavi.prayaas.R.drawable.nepalim;

/**
 * Created by chhavi on 2/6/15.
 */
public class HomeFragment extends Fragment {
ListView eventsList;
    LinearLayout back;
    private List<Events> events;
    private RecyclerView rv;
    String url;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_main,null);

     //   eventsList = (ListView)v.findViewById(R.id.eventsList);
        url = "http://192.168.0.102/android_login_api/fetch_events.php";
        RequestQueue queue = AppController.getInstance().getRequestQueue();

        GsonRequest<EventResponse> myreq = new GsonRequest<EventResponse>(Request.Method.GET,url,EventResponse.class, createMyReqSuccessListener(),
                createMyReqErrorListener());

        queue.add(myreq);

        rv=(RecyclerView)v.findViewById(R.id.rv);
        back = (LinearLayout)v.findViewById(R.id.home_background);
      //  back.setBackgroundColor(white);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
        initializeData();
        initializeAdapter();

      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
   //     eventsList.setAdapter(adapter);
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
        events.add(new Events("Event 1", "7/06/2015","Central Park", R.drawable.nepalim));
        events.add(new Events("Event 2", "7/06/2015","Central Park", R.drawable.nepalim));
        events.add(new Events("Event 3", "7/06/2015","Central Park", R.drawable.nepalim));
        events.add(new Events("Event 4", "7/06/2015","Central Park", R.drawable.nepalim));
        events.add(new Events("Emma Wilson", "7/06/2015","Central Park", R.drawable.nepalim));
    }

    private void initializeAdapter(){
        EventCardAdapter adapter;
        adapter = new EventCardAdapter(events);


        adapter.SetOnItemClickListener(new EventCardAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                // do something with position

                Intent i = new Intent(getActivity(),EventDetail.class);
                Events selectedEvent = events.get(position);
                i.putExtra("selectedEvent", (Serializable) selectedEvent);
                startActivity(i);
            }
        });
        rv.setAdapter(adapter);


    }
}
