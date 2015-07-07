package com.example.chhavi.prayaas;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.engio.prayaas.R;
import com.gc.materialdesign.views.ButtonRectangle;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutAppFragment extends android.support.v4.app.Fragment {
Button followFacebook;

    public AboutAppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about_app, container, false);
        ButtonRectangle feedback = (ButtonRectangle) v.findViewById(R.id.feedBack);
        feedback.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String recepientEmail = "prayaas.engio123@gmail.com"; // either set to destination email or leave empty
                                            Intent intent = new Intent(Intent.ACTION_SENDTO);
                                            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                                            intent.setData(Uri.parse("mailto:" + recepientEmail));
                                            startActivity(intent);
                                        }

                                    });
        followFacebook = (Button)v.findViewById(R.id.facebook_follow);
        final String path = "https://m.facebook.com/profile.php?id=698282173632058&ref=bookmarks";
        followFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setPackage("com.android.chrome");
                try {
                    startActivity(i);
                } catch (ActivityNotFoundException ex) {
                    // Chrome browser presumably not installed so allow user to
                    // choose instead
                    i.setPackage(null);
                    startActivity(i);
                    Log.e("chrome not found", "shesh");

                }

            }
        });
        return v;
    }


}




