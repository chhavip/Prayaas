package com.example.chhavi.prayaas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gc.materialdesign.views.ButtonRectangle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.AppConfig;
import app.AppController;
import helper.SQLiteHandler;
import helper.SessionManager;


public class EditProfileActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String TAG = EditProfileActivity.class.getSimpleName();
    SharedPreferences sp;
    EditText name;
    TextView phone;
    EditText email;
    EditText password;
    String userId;
    String Newname;
    String newEmail;
    String newPassword;
    String url;
    private SQLiteHandler db;

    HashMap<String,String> userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        db = new SQLiteHandler(this);
      //  View v = localInflater.inflate(R.layout.fragment_profile, container, false);
        SQLiteHandler handler = new SQLiteHandler(this);
        userInfo = handler.getUserDetails();
        sp = getSharedPreferences("Prayaas", Context.MODE_PRIVATE);
         name = (EditText) findViewById(R.id.edit_name);
//         phone = (TextView) findViewById(R.id.editPhone);
         email = (EditText) findViewById(R.id.edit_email);
         password = (EditText) findViewById(R.id.edit_password);
        name.setText(userInfo.get("name"));
        userId = userInfo.get("uid");
        url = AppConfig.BASE_URL;
        phone.setText(sp.getString(PrayaasContract.USER_TABLE_PHONE_COL, null));
        email.setText(userInfo.get("email"));
    //    password.setText(sp.getString(PrayaasContract.USER_TABLE_PASSWORD_COL, null));
        ButtonRectangle saveButton = (ButtonRectangle) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
/*
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PrayaasContract.USER_TABLE_NAME_COL, name.getText().toString());
        editor.putString(PrayaasContract.USER_TABLE_USERNAME_COL, email.getText().toString());
        editor.putString(PrayaasContract.USER_TABLE_PHONE_COL, phone.getText().toString());
        editor.putString(PrayaasContract.USER_TABLE_PASSWORD_COL, password.getText().toString());
        editor.commit();*/
        Newname = name.getText().toString();
        newEmail = email.getText().toString();
        newPassword = password.getText().toString();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                //     hideDialog();
           //     String tag_string_req = "req_register";

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                      //  session = new SessionManager(getApplicationContext());
                       // session.setLogin(true);
                        String uid = jObj.getString("uid");
                        Log.e("user_id",uid);
                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                     //   db.updateUser(name, email, uid);
                        db.deleteUsers();
                        db.addUser(name,email,uid,created_at);
                        Log.e("new details", name+email+uid);
                        Toast.makeText(getApplicationContext(),
                                "Details changed successfully", Toast.LENGTH_LONG).show();
                        // Launch login activity
                      /*  Intent intent = new Intent(
                                OtpActivity.this,
                                NavigationDrawer.class);
                        startActivity(intent);
                        finish();*/
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                //          hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "update");
                params.put("name", Newname);
                params.put("email", newEmail);
                params.put("uid", userId);
                params.put("password", newPassword);
              //  params.put("referral", referral);
               // params.put("age", age);
               // params.put("gender", gender);
                Log.e("Values", Newname + newEmail + userId + newPassword);



                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "req_register");
    }


    }

