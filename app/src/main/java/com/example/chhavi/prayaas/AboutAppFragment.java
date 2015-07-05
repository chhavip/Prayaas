package com.example.chhavi.prayaas;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutAppFragment extends android.support.v4.app.Fragment {


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
        return v;
    }


}
