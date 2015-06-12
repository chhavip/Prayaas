package com.example.chhavi.prayaas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shikharkhetan on 6/3/15.
 */
public class PrayaasSQLiteOpenHelper extends SQLiteOpenHelper {
    public PrayaasSQLiteOpenHelper(Context context) {
        super(context, PrayaasContract.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                +PrayaasContract.USER_TABLE_NAME_COL+" TEXT,"
                +PrayaasContract.USER_TABLE_AGE_COL+" INTEGER,"+
                PrayaasContract.USER_TABLE_USERNAME_COL+" TEXT,"+
                PrayaasContract.USER_TABLE_GENDER_COL+" TEXT,"+
                PrayaasContract.USER_TABLE_PHONE_COL+" TEXT,"+
                PrayaasContract.USER_TABLE_PASSWORD_COL+" TEXT,"+
                PrayaasContract.USER_TABLE_REFERRAL_COL+" TEXT"+

                ")");
        db.execSQL("CREATE TABLE User (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                +PrayaasContract.USER_TABLE_NAME_COL+" TEXT,"
                +PrayaasContract.USER_TABLE_AGE_COL+" INTEGER,"+
                PrayaasContract.USER_TABLE_USERNAME_COL+" TEXT,"+
                PrayaasContract.USER_TABLE_GENDER_COL+" TEXT,"+
                PrayaasContract.USER_TABLE_PHONE_COL+" TEXT,"+
                PrayaasContract.USER_TABLE_PASSWORD_COL+" TEXT,"+
                PrayaasContract.USER_TABLE_REFERRAL_COL+" TEXT"+

                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
