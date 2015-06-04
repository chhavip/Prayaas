package com.example.chhavi.prayaas;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button login;
    Button signUp;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences("Prayaas", Context.MODE_PRIVATE);
//        if(sp != null)  {
//            Intent i = new Intent();
//            i.setClass(this, NavigationDrawer.class);
//            startActivity(i);
//        }

        login = (Button) findViewById(R.id.login);
        signUp = (Button) findViewById(R.id.signup);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login.setOnClickListener(this);
        signUp.setOnClickListener(this);
        PrayaasSQLiteOpenHelper helper=new PrayaasSQLiteOpenHelper(this);
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PrayaasContract.USER_TABLE_NAME_COL, "shikhar");
        cv.put(PrayaasContract.USER_TABLE_USERNAME_COL, "user");
        cv.put(PrayaasContract.USER_TABLE_PASSWORD_COL, "password");
        cv.put(PrayaasContract.USER_TABLE_PHONE_COL, "9818115541");
        cv.put(PrayaasContract.USER_TABLE_AGE_COL, 20);
        cv.put(PrayaasContract.USER_TABLE_GENDER_COL, "Male");
        cv.put(PrayaasContract.USER_TABLE_REFERRAL_COL, "SH981");

        db.insert(PrayaasContract.USER_TABLE, null, cv);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

        if(v.getId() == R.id.login) {
            if (username.getText().toString() == null || password.getText().toString() == null) {
                Toast.makeText(this, "empty username/password", Toast.LENGTH_SHORT).show();
                return;
            }

            String userName = username.getText().toString();
            String passWord = password.getText().toString();

            PrayaasSQLiteOpenHelper helper = new PrayaasSQLiteOpenHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor c = db.query(PrayaasContract.USER_TABLE, new String[]    {
                            PrayaasContract.USER_TABLE_USERNAME_COL,
                            PrayaasContract.USER_TABLE_NAME_COL,
                            PrayaasContract.USER_TABLE_PASSWORD_COL,
                            PrayaasContract.USER_TABLE_PHONE_COL,
                            PrayaasContract.USER_TABLE_AGE_COL,
                            PrayaasContract.USER_TABLE_GENDER_COL,
                            PrayaasContract.USER_TABLE_REFERRAL_COL},

                    PrayaasContract.USER_TABLE_USERNAME_COL + " = " + "'" + userName + "'"
                            + " and " + PrayaasContract.USER_TABLE_PASSWORD_COL + " = "
                            + "'" + passWord + "'", null, null, null, null, null);

            Boolean right = c.moveToFirst();
            if (right) {
                Toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show();
                savePreferences(c);

                Intent i = new Intent();
                i.setClass(this, NavigationDrawer.class);
                startActivity(i);
            } else
                Toast.makeText(this, "invalid username/password", Toast.LENGTH_SHORT).show();


        }
        else if(v.getId() == R.id.signup)
        {
            Intent i = new Intent();
            i.setClass(this, SignUpActivity.class);
            startActivityForResult(i, 1);
        }
    }

    public void savePreferences(Cursor c)   {

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PrayaasContract.USER_TABLE_NAME_COL, c.getString(c.getColumnIndex(PrayaasContract.USER_TABLE_NAME_COL)));
        editor.putString(PrayaasContract.USER_TABLE_USERNAME_COL, c.getString(c.getColumnIndex(PrayaasContract.USER_TABLE_USERNAME_COL)));
        editor.putString(PrayaasContract.USER_TABLE_PHONE_COL, c.getString(c.getColumnIndex(PrayaasContract.USER_TABLE_PHONE_COL)));
        editor.putString(PrayaasContract.USER_TABLE_PASSWORD_COL, c.getString(c.getColumnIndex(PrayaasContract.USER_TABLE_PASSWORD_COL)));
        editor.putString(PrayaasContract.USER_TABLE_GENDER_COL, c.getString(c.getColumnIndex(PrayaasContract.USER_TABLE_GENDER_COL)));
        editor.putString(PrayaasContract.USER_TABLE_REFERRAL_COL, c.getString(c.getColumnIndex(PrayaasContract.USER_TABLE_REFERRAL_COL)));
        editor.putInt(PrayaasContract.USER_TABLE_AGE_COL, c.getInt(c.getColumnIndex(PrayaasContract.USER_TABLE_AGE_COL)));
        editor.commit();
    }


}
