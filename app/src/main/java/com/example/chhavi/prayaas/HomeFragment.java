package com.example.chhavi.prayaas;

import android.animation.ValueAnimator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import helper.PrayaasFragmentPagerAdapter;
import models.EventResponse;
import models.Events;
import android.support.v4.app.Fragment;
import static com.example.chhavi.prayaas.R.color.abc_primary_text_disable_only_material_dark;
import static com.example.chhavi.prayaas.R.color.white;
import static com.example.chhavi.prayaas.R.drawable.nepalim;

/**
 * Created by chhavi on 2/6/15.
 */
public class HomeFragment extends android.support.v4.app.Fragment{
//ListView eventsList;
//    RelativeLayout back;
//    private List<Events> events;
//    public List<Events> eventsGoing;
//    EventCardAdapter adapter1;
//    RelativeLayout rl;
//    private RecyclerView rv;
//    private RecyclerView rvMyEvents;
//    TextView tv;
//    String url;
//    TextView tv2;
//    int goingEventsSize;
    PrayaasFragmentPagerAdapter adapter;
    ViewPager pager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        adapter = new PrayaasFragmentPagerAdapter(this.getActivity().getSupportFragmentManager());
        pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setAdapter(adapter);


//
//        adapter1 = new EventCardAdapter(eventsGoing, R.layout.fragment_going_event);
//        adapter1.mOnCancelListener = this;
//        adapter1.SetOnItemClickListener(new EventCardAdapter.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(View v, int position) {
//                // do something with position
//
//                Intent i = new Intent(getActivity(),EventDetail.class);
//                Events selectedEvent = events.get(position);
//                i.putExtra("selectedEvent", (Serializable) selectedEvent);
//                startActivity(i);
//
//            }
//
//        });

//        rvMyEvents.setAdapter(adapter1);        //   eventsList = (ListView)v.findViewById(R.id.eventsList);
//        url = "http://192.168.0.102/android_login_api/fetch_events.php";
//        RequestQueue queue = AppController.getInstance().getRequestQueue();
//
//        GsonRequest<EventResponse> myreq = new GsonRequest<EventResponse>(Request.Method.GET,url,EventResponse.class, createMyReqSuccessListener(),
//                createMyReqErrorListener());
//
//        queue.add(myreq);
//
//        rv=(RecyclerView)v.findViewById(R.id.rv);
//      //  back.setBackgroundColor(white);
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
//
//        rv.setLayoutManager(llm);
//        rv.setHasFixedSize(true);
//        rv.setItemAnimator(new DefaultItemAnimator());
//        initializeData();
//        initializeAdapter();
//        if(eventsGoing.isEmpty())    {
//            rl = (RelativeLayout) v.findViewById(R.id.home_background);
//           rl.removeView(rvMyEvents);
//            tv = (TextView) v.findViewById(R.id.titleAllEvents);
//            tv2 = (TextView) v.findViewById(R.id.titleSelectedEvents);
//                tv2.setVisibility(View.GONE);
//            tv.setY(20);
//        }
        //Read database and check if user event database has any pending events. If yes, display using the following code
        //if cursor.moveToFirst!=null we can make pendingEvents true
        //The fragment has to be hidden when there is no pending event.
//        boolean pendingEvents = true;
//        if(pendingEvents)   {
//            //this shifts the layout by exactly the size of our fragment
//            RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.rl);
//            rl.setY(400);
//        }

        return v;
    }

//    public void onDestroyView() {
//        super.onDestroyView();
//        Fragment f = (Fragment) getFragmentManager()
//                .findFragmentById(R.id.goingFragment);
//        if (f != null)
//            getFragmentManager().beginTransaction().remove(f).commit();
//    }
//
//    private Response.Listener<EventResponse> createMyReqSuccessListener() {
//        return new Response.Listener<EventResponse>() {
//            @Override
//            public void onResponse(EventResponse response) {
//                // Do whatever you want to do with response;
//                // Like response.tags.getListing_count(); etc. etc.
//                List<EventResponse.EventModel> events = new ArrayList<EventResponse.EventModel>();
//                events = response.getEvents();
//                Log.e("response", events.get(0).getName());
//
//            }
//        };
//    }
//    private Response.ErrorListener createMyReqErrorListener() {
//        return new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // Do whatever you want to do with error.getMessage();
//            }
//        };
//    }
//    private void initializeData(){
//        events = new ArrayList<>();
//
//
//        events.add(new Events("Event 1", "7/06/2015","Central Park", R.drawable.logo));
//        events.add(new Events("Event 2", "7/06/2015","Central Park", R.drawable.logo));
//        events.add(new Events("Event 3", "7/06/2015","Central Park", R.drawable.nepalim));
//        events.add(new Events("Event 4", "7/06/2015","Central Park", R.drawable.logo));
//        events.add(new Events("Emma Wilson", "7/06/2015","Central Park", R.drawable.nepalim));
//    }
//
//    private void initializeAdapter(){
//        EventCardAdapter adapter;
//        adapter = new EventCardAdapter(events, R.layout.event_card_item);
//
//
//        adapter.SetOnItemClickListener(new EventCardAdapter.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(View v, int position) {
//                // do something with position
//
//                Intent i = new Intent(getActivity(),EventDetail.class);
//                Events selectedEvent = events.get(position);
//                i.putExtra("selectedEvent", (Serializable) selectedEvent);
//                startActivityForResult(i, 1);
//            }
//        });
//        rv.setAdapter(adapter);
//
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            Bundle b = data.getExtras();
//
////            if(rl.findViewById(R.id.myEventsrv) == null)      {
////                rl.addView(rvMyEvents);
////
////            }
//            Events selectedEvent = (Events) b.get("selectedEvent");
//            if (resultCode == 1) {
//                //going
//                if(eventsGoing.isEmpty())   {
//
//                    tv.setY(80);
//                    rv.setY(115);
//                    tv2.setVisibility(View.VISIBLE);
//                    rvMyEvents.setVisibility(View.VISIBLE);
//
//                }
//
//                eventsGoing.add(selectedEvent);
//                adapter1.notifyDataSetChanged();
//
//
//
//            } else if (resultCode == 2) {
//                //notSure
////                eventsGoing.add(selectedEvent);
////                adapter1.notifyDataSetChanged();
////                tv.setY(230);
////                rv.setY(270);
////                tv2.setVisibility(View.VISIBLE);
////                rvMyEvents.setVisibility(View.VISIBLE);
//                if(eventsGoing.isEmpty())   {
//                    tv.setY(80);
//                    rv.setY(115);
//                    tv2.setVisibility(View.VISIBLE);
//                    rvMyEvents.setVisibility(View.VISIBLE);
//
//                }
//                eventsGoing.add(selectedEvent);
//                adapter1.notifyDataSetChanged();
//
//            }
//        }
//
//    }
//
//
//    @Override
    public void onCancelButtonPressed(View v) {
//
//        adapter1.notifyDataSetChanged();
//
//        if (eventsGoing.isEmpty()) {
//            ValueAnimator animator = ValueAnimator.ofFloat(tv.getY(), 20);
//            animator.setDuration(500);
//            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    tv.setY((Float) animation.getAnimatedValue());
//                    rv.setY((Float) animation.getAnimatedValue()+ 35);
//                }
//            });
//            animator.start();
//            tv2.setVisibility(View.GONE);
//            rvMyEvents.setVisibility(View.GONE);
//        }
//
    }
}
