package com.example.chhavi.prayaas;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.engio.prayaas.R;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.List;

/**
 * Created by chhavi on 3/6/15.
 */
public class InviteFriends  extends android.support.v4.app.Fragment {
    ButtonRectangle whatsappShare;
    ButtonRectangle facebookShare;
    ButtonRectangle emailShare;
    ButtonRectangle more;
    public Intent sendIntent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.invite_layout,null);
        whatsappShare = (ButtonRectangle)v.findViewById(R.id.whatsapp_button);
       facebookShare = (ButtonRectangle)v.findViewById(R.id.facebook_button);
        emailShare = (ButtonRectangle)v.findViewById(R.id.gmail_button);
        more = (ButtonRectangle)v.findViewById(R.id.more_options_button);

        whatsappShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, Constants.SHARE_US_TEXT);
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(sendIntent, 0);
                boolean isIntentSafe = activities.size() > 0;
                if(isIntentSafe)
                    startActivity(sendIntent);
                else
                    Toast.makeText(getActivity(), "Whatsapp not installed", Toast.LENGTH_LONG).show();
            }
        });

        emailShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("plain/text");
                //	sendIntent.setData(Uri.parse("test@gmail.com"));
                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                //		sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "test@gmail.com" });
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Prayaas");
                sendIntent.putExtra(Intent.EXTRA_TEXT, Constants.SHARE_US_TEXT);

                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(sendIntent, 0);
                boolean isIntentSafe = activities.size() > 0;
                if(isIntentSafe)
                    //	startActivity(sendIntent);
                    startActivity(sendIntent);
                else
                    Toast.makeText(getActivity(), "Gmail not installed", Toast.LENGTH_LONG).show();
            }
        });

        facebookShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, Constants.SHARE_US_URL);
                sendIntent.setPackage("com.facebook.katana");

                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(sendIntent, 0);
                boolean isIntentSafe = activities.size() > 0;
                if(isIntentSafe)
                    //	startActivity(sendIntent);
                    startActivity(sendIntent);
                else
                    Toast.makeText(getActivity(), "Facebook not installed", Toast.LENGTH_LONG).show();
            }
        });

        more.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, Constants.SHARE_US_TEXT);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });


        return v;
    }
}
