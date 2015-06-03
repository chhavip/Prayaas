package com.example.chhavi.prayaas;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;

/**
 * Created by chhavi on 31/5/15.
 */
public class NavigationDrawer extends MaterialNavigationDrawer {
    @Override
    public void init(Bundle bundle) {
        this.addSection(newSection("HOME", new HomeFragment()));
        this.addSection(newSection("PROFILE", new FragmentOne()));
        this.addSection(newSection("REWARDS", new FragmentOne()));
        this.addSection(newSection("INVITE FRIENDS", new InviteFriends()));
        this.addSection(newSection("ABOUT US", new FragmentOne()));
       this.addAccount(new MaterialAccount(this.getResources(),"Chhavi Gupta","coolaqua12@gmail.com",null,R.drawable.nepalim));

    }
}
