package com.example.chhavi.prayaas;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;

/**
 * Created by chhavi on 8/6/15.
 */
public class Rewards extends Fragment {
    ButtonRectangle donateButton;
    ButtonRectangle collectButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_rewards,null);
        donateButton = (ButtonRectangle)v.findViewById(R.id.donate_button);
        collectButton = (ButtonRectangle)v.findViewById(R.id.collect_button);

        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),DonateActivity.class);
                startActivity(i);
            }
        });


        return v;
    }
}
