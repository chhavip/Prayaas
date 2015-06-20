package com.example.chhavi.prayaas;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.gc.materialdesign.views.ButtonRectangle;


public class EditProfileActivity extends ActionBarActivity implements View.OnClickListener {

    SharedPreferences sp;
    EditText name;
    EditText phone;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sp = getSharedPreferences("Prayaas", Context.MODE_PRIVATE);
         name = (EditText) findViewById(R.id.edit_name);
         phone = (EditText) findViewById(R.id.editPhone);
         email = (EditText) findViewById(R.id.edit_email);
         password = (EditText) findViewById(R.id.edit_password);
        name.setText(sp.getString(PrayaasContract.USER_TABLE_NAME_COL, null));
        phone.setText(sp.getString(PrayaasContract.USER_TABLE_PHONE_COL, null));
        email.setText(sp.getString(PrayaasContract.USER_TABLE_USERNAME_COL,null));
        password.setText(sp.getString(PrayaasContract.USER_TABLE_PASSWORD_COL, null));
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

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PrayaasContract.USER_TABLE_NAME_COL, name.getText().toString());
        editor.putString(PrayaasContract.USER_TABLE_USERNAME_COL, email.getText().toString());
        editor.putString(PrayaasContract.USER_TABLE_PHONE_COL, phone.getText().toString());
        editor.putString(PrayaasContract.USER_TABLE_PASSWORD_COL, password.getText().toString());
        editor.commit();


    }
}
