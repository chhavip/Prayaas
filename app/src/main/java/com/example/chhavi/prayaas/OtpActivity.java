package com.example.chhavi.prayaas;

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


public class OtpActivity extends ActionBarActivity implements View.OnClickListener {

    EditText otpInput;
    String desiredOtp;
    ContentValues cv;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("Prayaas", Context.MODE_PRIVATE);

        setContentView(R.layout.activity_otp);
        TextView phoneNumber = (TextView) findViewById(R.id.displayPhone);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        cv = (ContentValues) b.get("userdata");
        otpInput = (EditText) findViewById(R.id.otp);
        desiredOtp =  cv.getAsString(PrayaasContract.USER_TABLE_NAME_COL).substring(0,2) + cv.getAsString(PrayaasContract.USER_TABLE_PHONE_COL).substring(7);
        String phoneNum = (String) cv.get(PrayaasContract.USER_TABLE_PHONE_COL);
        phoneNumber.setText(phoneNum);
        SmsManager smsManager = SmsManager.getDefault();
        String message = "This is an otp request from "+ phoneNum + " name is " + cv.get(PrayaasContract.USER_TABLE_NAME_COL);
        //smsManager.sendTextMessage("9873371087", null, message, null, null);
        Button button = (Button) findViewById(R.id.verifyButton);
        button.setOnClickListener(this);
        Log.i("desired", desiredOtp);

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
        String otpInputString = otpInput.getText().toString();
        if(otpInputString.equals(desiredOtp))  {
            PrayaasSQLiteOpenHelper helper = new PrayaasSQLiteOpenHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            db.insert(PrayaasContract.USER_TABLE, null, cv);
            savePreferences(cv);
            Intent i = new Intent();
            i.setClass(this, NavigationDrawer.class);
         //   i.putExtra("userdata", cv);
            startActivity(i);
        }


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
}
