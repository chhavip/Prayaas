package com.example.chhavi.prayaas;


import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gc.materialdesign.views.ButtonRectangle;
import com.melnykov.fab.FloatingActionButton;

import java.util.HashMap;

import app.AppConfig;
import app.AppController;
import helper.GsonRequest;
import helper.SQLiteHandler;
import helper.SessionManager;
import models.EventResponse;
import models.UserDetailResponse;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends android.support.v4.app.Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView email;
    TextView name;
    public static UserDetailResponse userDetailResponse;
    TextView referral;
    TextView age;
    TextView gender;
    TextView phoneNumber;
    HashMap<String,String> userInfo;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ButtonRectangle logout;
    ButtonRectangle fab;
    SharedPreferences sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity() , R.style.profile);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        // inflate the layout using the cloned inflater, not default inflater

        View v = localInflater.inflate(R.layout.fragment_profile, container, false);
        String url = AppConfig.BASE_URL + "fetch_user_details.php?userid=";
        SQLiteHandler handler = new SQLiteHandler(getActivity());
        userInfo = handler.getUserDetails();
        url = url+userInfo.get("uid");
        fetchUserDetails(url);
        sp = getActivity().getSharedPreferences("Prayaas", Context.MODE_PRIVATE);

        name = (TextView) v.findViewById(R.id.profileFragmentName);
        referral = (TextView)v.findViewById(R.id.profileFragmentReferral);
        age = (TextView)v.findViewById(R.id.profileFragmentAge);
        gender = (TextView)v.findViewById(R.id.profileFragmentGender);
        phoneNumber = (TextView)v.findViewById(R.id.phoneNumber);


        // TextView phone = (TextView) v.findViewById(R.id.profileFragmentPhone);

        email = (TextView) v.findViewById(R.id.profileFragmentEmail);
        // name.setText(sp.getString(PrayaasContract.USER_TABLE_NAME_COL, null));
        name.setText(userInfo.get("name"));
     //   phone.setText(userInfo.get(""));
     /*   logout = (ButtonRectangle) v.findViewById(R.id.LogOut);
        logout.setOnClickListener(this);
        fab = (ButtonRectangle) v.findViewById(R.id.fab);
        fab.setOnClickListener(this);*/
        email.setText(userInfo.get("email"));
        return v;

    }

    private void fetchUserDetails(String url) {

        RequestQueue queue = AppController.getInstance().getRequestQueue();

        GsonRequest<UserDetailResponse> myreq = new GsonRequest<UserDetailResponse>(Request.Method.GET,url,UserDetailResponse.class, createMyReqSuccessListener(),
                createMyReqErrorListener());

        queue.add(myreq);
    }

    private Response.Listener<UserDetailResponse> createMyReqSuccessListener() {
        return new Response.Listener<UserDetailResponse>() {
            @Override
            public void onResponse(UserDetailResponse userDetailResponse) {
                ProfileFragment.userDetailResponse = userDetailResponse;
                Log.e("Details",userDetailResponse.getAge());
                name.setText(userDetailResponse.getName());
                age.setText(userDetailResponse.getAge() + " years");
                email.setText(userDetailResponse.getEmail());
                gender.setText(userDetailResponse.getGender());
                phoneNumber.setText(userDetailResponse.getPhonenumber());
                referral.setText(userDetailResponse.getReferral());
            }
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        };
    }


   /* @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab)   {
            //TODO edit profile
           *//* AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
            b.setTitle("Enter Password");

            View v1 = getActivity().getLayoutInflater().inflate(R.layout.confirm_password_dialog, null);
            b.setView(v1);
            final EditText editText = (EditText) v1.findViewById(R.id.passwordConfirm);
            b.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(editText.getText().toString().equals(sp.getString(PrayaasContract.USER_TABLE_PASSWORD_COL, null)))    {*//*
                        Intent i = new Intent();
                        i.setClass(getActivity(), EditProfileActivity.class);
                        startActivity(i);
                 *//*   }
                    else
                    {
                        Toast.makeText(getActivity(), sp.getString(PrayaasContract.USER_TABLE_PASSWORD_COL, null), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            b.create().show();*//*
        }
        else {
            SessionManager sessionManager = new SessionManager(getActivity());
            sessionManager.setLogin(false);
            Intent i = new Intent();
            i.setClass(getActivity(), LoginActivityNet.class);
            startActivity(i);
            getActivity().finish();

        }
    }*/
}
