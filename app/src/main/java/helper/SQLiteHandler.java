/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chhavi.prayaas.PrayaasContract;

import java.util.HashMap;

import models.EventResponse;

public class SQLiteHandler extends SQLiteOpenHelper {

	private static final String TAG = SQLiteHandler.class.getSimpleName();

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "android_api";

	// Login table name
	private static final String TABLE_LOGIN = "login";

	// Login Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_UID = "uid";
	private static final String KEY_CREATED_AT = "created_at";

	public SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"
				+ KEY_CREATED_AT + " TEXT" + ")";

		String CREATE_EVENT_TABLE = "CREATE TABLE events (EVENT_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ PrayaasContract.EVENT_TABLE_NAME+" TEXT,"
				+PrayaasContract.EVENT_TABLE_SEATS+" INTEGER,"+
				PrayaasContract.EVENT_TABLE_DESCRIPTION+" TEXT,"+
				PrayaasContract.EVENT_TABLE_CONTACT+" TEXT,"+
				PrayaasContract.EVENT_TABLE_DATE+" TEXT,"+
				PrayaasContract.EVENT_TABLE_DURATION+" TEXT,"+
				PrayaasContract.EVENT_TABLE_STATUS+" TEXT,"+
				PrayaasContract.EVENT_TABLE_TIME+" TEXT,"+
				PrayaasContract.EVENT_TABLE_VENUE+" TEXT,"+
				PrayaasContract.EVENT_TABLE_ORGANISATION+" TEXT"+

				")";
		db.execSQL(CREATE_LOGIN_TABLE);
		db.execSQL(CREATE_EVENT_TABLE);
		Log.d(TAG, "Database tables created");
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */

	public void addEvent(EventResponse.EventModel event)	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(PrayaasContract.EVENT_TABLE_NAME, event.getName());
		cv.put(PrayaasContract.EVENT_TABLE_DESCRIPTION, event.getDescription());
		cv.put(PrayaasContract.EVENT_TABLE_TIME, event.getTime());
		cv.put(PrayaasContract.EVENT_TABLE_STATUS, event.getStatus());
		cv.put(PrayaasContract.EVENT_TABLE_CONTACT, event.getContact());
		cv.put(PrayaasContract.EVENT_TABLE_SEATS, event.getSeats());
		cv.put(PrayaasContract.EVENT_TABLE_ORGANISATION, event.getOrganisation());
		cv.put(PrayaasContract.EVENT_TABLE_DURATION, event.getDuration());
		cv.put(PrayaasContract.EVENT_TABLE_DATE, event.getDate());
		long id = db.insert(PrayaasContract.EVENT_TABLE, null, cv);
		db.close();

	}

	public void addUser(String name, String email, String uid, String created_at) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, name); // Name
		values.put(KEY_EMAIL, email); // Email
		values.put(KEY_UID, uid); // Email
		values.put(KEY_CREATED_AT, created_at); // Created At

		// Inserting Row
		long id = db.insert(TABLE_LOGIN, null, values);
		db.close(); // Closing database connection

		Log.d(TAG, "New user inserted into sqlite: " + id);
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.put("name", cursor.getString(1));
			user.put("email", cursor.getString(2));
			user.put("uid", cursor.getString(3));
			user.put("created_at", cursor.getString(4));
		}
		cursor.close();
		db.close();
		// return user
		Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

		return user;
	}

	/**
	 * Getting user login status return true if rows are there in table
	 * */
	public int getRowCount(String tableName) {
		String countQuery = "SELECT  * FROM " + tableName;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();

		// return row count
		return rowCount;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_LOGIN, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}

}
