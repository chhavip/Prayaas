package com.example.chhavi.prayaas;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by chhavi on 31/5/15.
 */
public class NavigationDrawer extends MaterialNavigationDrawer {
    @Override
    public void init(Bundle bundle) {
        this.addSection(newSection("HOME", new HomeFragment()));
        this.addSection(newSection("PROFILE", new FragmentOne()));
        this.addSection(newSection("REWARDS", new FragmentOne()));
        this.addSection(newSection("MY EVENTS", new FragmentOne()));
        this.addSection(newSection("ABOUT US", new FragmentOne()));


    }
}
