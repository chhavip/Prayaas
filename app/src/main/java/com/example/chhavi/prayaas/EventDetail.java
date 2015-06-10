package com.example.chhavi.prayaas;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by chhavi on 8/6/15.
 */
public class EventDetail extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_detail_layout);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
}
