package com.example.chhavi.prayaas;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by chhavi on 31/5/15.
 */
public class NavigationDrawer extends MaterialNavigationDrawer {
    @Override
    public void init(Bundle bundle) {

        this.addSection(newSection("title 1", new FragmentOne()));

    }
}
