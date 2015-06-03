package com.example.chhavi.prayaas;

import android.provider.BaseColumns;

/**
 * Created by shikharkhetan on 6/2/15.
 */
public class PrayaasContract implements BaseColumns {

    private PrayaasContract(){}

    public static final String DB_NAME = "Prayaas";
    public static final String USER_TABLE="User";
    public static final String USER_TABLE_USERNAME_COL="User_Username";
    public static final String USER_TABLE_PASSWORD_COL="Password";
    public static final String USER_TABLE_NAME_COL="Name";
    public static final String USER_TABLE_AGE_COL="Age";
    public static final String USER_TABLE_PHONE_COL="Phone";
    public static final String USER_TABLE_GENDER_COL="Gender";
    public static final String USER_TABLE_REFERRAL_COL="Referral";




}
