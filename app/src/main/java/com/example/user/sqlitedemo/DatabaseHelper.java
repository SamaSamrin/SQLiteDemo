package com.example.user.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 26-Apr-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    DatabaseHelper(Context context){
        super(context, "DietBoss", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
