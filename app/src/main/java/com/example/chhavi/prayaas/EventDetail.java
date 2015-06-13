package com.example.chhavi.prayaas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

import models.EventResponse;
import models.Events;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_scroll_event_details);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        events = (Events) b.get("selectedEvent");
        mImageView = (ImageView) findViewById(R.id.image);
        mImageView.setImageResource(events.imageResource);
        mToolbarView = (Toolbar) findViewById(R.id.toolbar);
        mToolbarView.setTitle(events.name);
        mToolbarView.setBackgroundColor(
                ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.white)));
        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);
        going = (ButtonRectangle) findViewById(R.id.goingButton);
        notSure = (ButtonRectangle) findViewById(R.id.notSureButton);
        going.setOnClickListener(this);
        notSure.setOnClickListener(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(
                R.dimen.parallax_image_height);
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
        if(v.getId() == R.id.goingButton)   {
            //Update in user event database. Add a tuple with event name and user name and going.
            Intent i = new Intent();
            i.putExtra("selectedEvent",events);
            setResult(1, i);
            finish();
        }
        else    {
            Intent i = new Intent();
            i.putExtra("selectedEvent",events);
            setResult(2,i);
            finish();
            //Update in user event database. Add a tuple with event name and user name and not sure.
        }
    }
}
