package com.example.user.sqlitedemo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ASUS on 4/26/2017.
 */

/**
 * This class is our DOA. It maintains the database connection and supports adding new data and
 * fetching all data.
 */
class UsersDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper helper;

    private String[] allColumns = {"_id", "username", "password", "email", "gender", "height", "initialWeight",
    "goalweight", "currentWeight"};

    public UsersDataSource(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }



}
