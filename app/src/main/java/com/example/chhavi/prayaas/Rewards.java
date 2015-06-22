package com.example.chhavi.prayaas;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        TextView tv= (TextView) v.findViewById(R.id.textWallet);
        String amount = (String) tv.getText();
        SharedPreferences sp = getActivity().getSharedPreferences("Prayaas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PrayaasContract.USER_TABLE_WALLET, amount);
        editor.commit();
        donateButton = (ButtonRectangle)v.findViewById(R.id.donate_button);
        collectButton = (ButtonRectangle)v.findViewById(R.id.collect_button);
        collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getActivity(), CollectActivity.class);
                startActivity(i);
            }
        });
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
