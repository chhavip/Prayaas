package com.example.chhavi.prayaas;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by chhavi on 2/6/15.
 */
public class HomeFragment extends Fragment {
ListView eventsList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_main,null);

        eventsList = (ListView)v.findViewById(R.id.eventsList);

        String[] values = new String[] { "Android List View",
                "Event 1",
                "Event 2",
                "Event 3",
                "Android Example",
                "List View Source Code",
                "Random text",
                "Android Example List View"
        };


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
        eventsList.setAdapter(adapter);
        return v;
    }
}
