package com.example.user.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 26-Apr-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "**DATABASE HELPER**";

    DatabaseHelper(Context context){
        super(context, "DietBoss", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            //creating tables
            db.execSQL("CREATE TABLE Users ( " +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT, " +
                    "password TEXT, " +
                    "email TEXT, " +
                    "gender TEXT, " +
                    "height REAL, " +
                    "initialWeight REAL, " +
                    "currentWeight REAL, " +
                    "goalWeight REAL ) ; ");
            Log.e(TAG, "users table created");

        } catch (Exception e){
            Log.e(TAG, "some table not created");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, DatabaseHelper.class.getName()+" upgrading db version from "+oldVersion+" to "+newVersion);
        //tasks you need to for upgraded version
        onCreate(db);
    }
}
