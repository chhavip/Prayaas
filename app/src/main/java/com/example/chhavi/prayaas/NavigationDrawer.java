package com.example.chhavi.prayaas;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;

/**
 * Created by chhavi on 31/5/15.
 */
public class NavigationDrawer extends MaterialNavigationDrawer {
    SharedPreferences sp;
    @Override
    public void init(Bundle bundle) {
        sp = getSharedPreferences("Prayaas", Context.MODE_PRIVATE);
        String name = sp.getString(PrayaasContract.USER_TABLE_NAME_COL, null);
        String username = sp.getString(PrayaasContract.USER_TABLE_USERNAME_COL, null);
        this.addSection(newSection("HOME", new HomeFragment()));
        this.addSection(newSection("PROFILE", new ProfileFragment()));
        this.addSection(newSection("REWARDS", new Rewards()));
        this.addSection(newSection("INVITE FRIENDS", new InviteFriends()));
        this.addSection(newSection("ABOUT APP", new AboutAppFragment()));
       this.addAccount(new MaterialAccount(this.getResources(), name, username,null,R.drawable.nepalim));

    }
}
