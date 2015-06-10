package com.example.chhavi.prayaas;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends ActionBarActivity implements View.OnClickListener {

    EditText name;
    EditText username;
    EditText password;
    EditText phone;
    EditText age;
    EditText referral;
    String gender;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.newname);
        username = (EditText) findViewById(R.id.newusername);
        password = (EditText) findViewById(R.id.newpassword);
        phone = (EditText) findViewById(R.id.newphone);
        age = (EditText) findViewById(R.id.newage);
        referral = (EditText) findViewById(R.id.newreferral);
        signUp = (Button) findViewById(R.id.newsignup);
        signUp.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

    public void maleClick(View v) {
        gender = "male";

    }
    public void femaleClick(View v) {
        gender = "female";

    }

    @Override
    public void onClick(View v) {
//      The signup page is not error proof right now.
        ContentValues cv = new ContentValues();
        cv.put(PrayaasContract.USER_TABLE_NAME_COL, name.getText().toString());
        cv.put(PrayaasContract.USER_TABLE_USERNAME_COL, username.getText().toString());
        cv.put(PrayaasContract.USER_TABLE_PASSWORD_COL, password.getText().toString());
        cv.put(PrayaasContract.USER_TABLE_PHONE_COL, phone.getText().toString());
        cv.put(PrayaasContract.USER_TABLE_AGE_COL, Integer.valueOf(age.getText().toString()));
        cv.put(PrayaasContract.USER_TABLE_GENDER_COL, gender);
        if(referral.getText().toString()!=null)
        cv.put(PrayaasContract.USER_TABLE_REFERRAL_COL, referral.getText().toString());
        Intent i = new Intent();
        i.setClass(this, OtpActivity.class);
        i.putExtra("userdata", cv);
        startActivity(i);
//        SharedPreferences sp = getSharedPreferences("Prayaas", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
        //TODO
//        If user returns to this screen, should the data in the entry fields be there or should they vanish

    }
}
