package com.example.chhavi.prayaas;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

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

        this.addSection(newSection("All Events", new AllEventsFragment()));
        this.addSection(newSection("My Events", new MyEventsFragment()));
        this.addSection(newSection("Profile", new ProfileFragment()));
        this.addSection(newSection("Rewards", new Rewards()));
        this.addSection(newSection("Invite Friends", new InviteFriends()));
        this.addSection(newSection("About App", new AboutAppFragment()));

    }



}
