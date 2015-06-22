package com.example.chhavi.prayaas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

public class CollectActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        android.support.v7.widget.Toolbar tool = (android.support.v7.widget.Toolbar) findViewById(R.id.tool);
        setSupportActionBar(tool);
    }


}
