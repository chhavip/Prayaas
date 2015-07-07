package com.example.chhavi.prayaas;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothProfile;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.engio.prayaas.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import app.AppConfig;
import app.AppController;
import helper.OtpSenderAsyncTask;
import helper.SQLiteHandler;
import helper.SessionManager;
import models.OtpContent;


public class OtpActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String TAG = OtpActivity.class.getSimpleName();
    EditText otpInput;
    int desiredOtp;
    String referral;
    private ProgressDialog pDialog;
    ContentValues cv;
    boolean flag = false;
    SharedPreferences sp;
    private SessionManager session;
    private SQLiteHandler db;
    String phoneNum;
    String message;
    private void showRandomInteger(int aStart, int aEnd, Random aRandom){
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        desiredOtp =  (int)(fraction + aStart);
       // Log.i("desired", desiredOtp + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("Prayaas", Context.MODE_PRIVATE);
        db = new SQLiteHandler(this);
        setContentView(R.layout.activity_otp);
        TextView phoneNumber = (TextView) findViewById(R.id.displayPhone);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        cv = (ContentValues) b.get("userdata");
        otpInput = (EditText) findViewById(R.id.otp);
        referral =  cv.getAsString(PrayaasContract.USER_TABLE_NAME_COL).substring(0,2) + cv.getAsString(PrayaasContract.USER_TABLE_PHONE_COL).substring(7);
       Random random = new Random();
        showRandomInteger(1000, 9999, random);

        phoneNum = (String) cv.get(PrayaasContract.USER_TABLE_PHONE_COL);
        phoneNumber.setText(phoneNum);
//        SmsManager smsManager = SmsManager.getDefault();
//        String message = "This is an otp request from "+ phoneNum + " name is " + cv.get(PrayaasContract.USER_TABLE_NAME_COL);
//        //smsManager.sendTextMessage("9873371087", null, message, null, null);
        Button button = (Button) findViewById(R.id.verifyButton);
        button.setOnClickListener(this);
        message = "The otp for the number " + phoneNum + " is " + desiredOtp;
        sendOtpRequest();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_otp, menu);
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
        if(!flag) {
            int otpInputString = Integer.valueOf(otpInput.getText().toString());
            if (otpInputString == desiredOtp) {
                //     PrayaasSQLiteOpenHelper helper = new PrayaasSQLiteOpenHelper(this);
                //    SQLiteDatabase db = helper.getWritableDatabase();
                //   db.insert(PrayaasContract.USER_TABLE, null, cv);
                //   savePreferences(cv);
//            Intent i = new Intent();
//            i.setClass(this, NavigationDrawer.class);
//            i.putExtra("userdata", cv);
//            startActivityr(i);

                String name = cv.getAsString(PrayaasContract.USER_TABLE_NAME_COL);
                String email = cv.getAsString(PrayaasContract.USER_TABLE_USERNAME_COL);
                String password = cv.getAsString(PrayaasContract.USER_TABLE_PASSWORD_COL);
                String phone = cv.getAsString(PrayaasContract.USER_TABLE_PHONE_COL);
                String age = cv.getAsString(PrayaasContract.USER_TABLE_AGE_COL);
                String gender = cv.getAsString(PrayaasContract.USER_TABLE_GENDER_COL);

                Log.e("data", email + password + phone + age + gender);

                registerUser(name, email, phone, password, referral, age, gender);
                Toast.makeText(OtpActivity.this,"Please give us a moment to verify your details",Toast.LENGTH_LONG).show();
            }
            flag = true;

        }
    }

    public void sendOtpRequest() {

        Toast.makeText(this, "A One-Time Password has been sent to your device, Please enter it in the space above" , Toast.LENGTH_LONG).show();
        OtpSenderAsyncTask task = new OtpSenderAsyncTask(this);
        OtpContent otpContent = new OtpContent(message, phoneNum);
        task.execute(otpContent);
    }

    public void savePreferences(ContentValues cv)   {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PrayaasContract.USER_TABLE_NAME_COL,cv.getAsString(PrayaasContract.USER_TABLE_NAME_COL));
        editor.putString(PrayaasContract.USER_TABLE_USERNAME_COL, cv.getAsString(PrayaasContract.USER_TABLE_USERNAME_COL));
        editor.putString(PrayaasContract.USER_TABLE_PHONE_COL, cv.getAsString(PrayaasContract.USER_TABLE_PHONE_COL));
        editor.putString(PrayaasContract.USER_TABLE_PASSWORD_COL, cv.getAsString(PrayaasContract.USER_TABLE_PASSWORD_COL));
        editor.putString(PrayaasContract.USER_TABLE_GENDER_COL, cv.getAsString(PrayaasContract.USER_TABLE_GENDER_COL));
        editor.putString(PrayaasContract.USER_TABLE_REFERRAL_COL, cv.getAsString(PrayaasContract.USER_TABLE_REFERRAL_COL));
        editor.putInt(PrayaasContract.USER_TABLE_AGE_COL, cv.getAsInteger(PrayaasContract.USER_TABLE_AGE_COL));
        editor.commit();
    }

    private void registerUser(final String name, final String email,final String phone,
                              final String password, final String referral,final String age, final String gender) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";
        //TODO send referral code and phone number as well.

        // pDialog.setMessage("Registering ...");
      //  showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
              //  Log.d(TAG, "Register Response: " + response.toString());
           //     hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        session = new SessionManager(getApplicationContext());
                        session.setLogin(true);
                        String uid = jObj.getString("uid");
                        Log.e("user_id",uid);
                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user
                                .getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid ,created_at);


                        // Launch login activity
                        Intent intent = new Intent(
                                OtpActivity.this,
                                NavigationDrawer.class);
                        startActivity(intent);
                        finish();
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
                params.put("tag", "register");
                params.put("name", name);
                params.put("email", email);
                params.put("number", phone);
                params.put("password", password);
                params.put("referral", referral);
                params.put("age", age);
                params.put("gender", gender);



                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
