package com.example.user.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 4/26/2017.
 */

/**
 * This class is one of our DOAs or Data Access Objects. It's responsible for handling the database connection and for accessing and modifying
 * the data. It will also convert the database objects into real Java Objects, so that our user interface code does not have to deal with
 * the persistence layer.
 */
class UsersDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper helper;

    private String[] allColumns = {"_id", "username", "password", "email", "gender", "height", "initialWeight",
    "goalweight", "currentWeight"};

     UsersDataSource(Context context) {
        helper = new DatabaseHelper(context);
    }

     void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

     void close() {
        helper.close();
    }

     User createUser(String username/*, String password*/){
        //User user = new User(email, password);//redundant
        ContentValues values = new ContentValues();
        values.put("username", username);
        //values.put("password", password);
        long insertId = database.insert("Users", null, values);//ERROR//
        Cursor cursor = database.query("Users", allColumns, "_id  = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        User user = cursorToUser(cursor);//ERROR//
        cursor.close();
        return user;
    }

     void deleteUser(User user){
        long id = user.getId();
        database.delete("Users", "_id ="+id, null);//delete the given id (matching with the "_id" column) from the Users table
    }

     List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        Cursor cursor = database.query("Users", allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }
    private User cursorToUser(Cursor cursor){
        User user = new User();
        user.id = cursor.getLong(0);//ERROR//
        user.username = cursor.getString(1);
        return user;
    }

}
