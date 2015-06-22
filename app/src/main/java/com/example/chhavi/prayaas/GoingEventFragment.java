package com.example.chhavi.prayaas;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import helper.EventCardAdapter;
import models.Events;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoingEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoingEventFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RelativeLayout back;
    private List<Events> events;
    private RecyclerView rv;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoingEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoingEventFragment newInstance(String param1, String param2) {
        GoingEventFragment fragment = new GoingEventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GoingEventFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.going_fragment_list, container, false);
        //Fetch data from database and display here
        //if going table is empty, dont do anything
        //if going table is not empty, display data
        rv=(RecyclerView)v.findViewById(R.id.rv);
        //  back.setBackgroundColor(white);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
        initializeData();
        initializeAdapter();
    return v;
    }

    public void cancelEventClick(View v)    {

    }
    private void initializeData(){
        events = new ArrayList<>();
        events.add(new Events("Event 1", "7/06/2015","Central Park", R.drawable.nepalim));
        events.add(new Events("Event 2", "7/06/2015","Central Park", R.drawable.nepalim));
        events.add(new Events("Event 3", "7/06/2015","Central Park", R.drawable.nepalim));

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
                startActivity(i);
            }
        });
        rv.setAdapter(adapter);


    }
}
