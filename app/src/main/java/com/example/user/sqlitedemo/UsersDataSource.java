package com.example.user.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    private static final String TAG = "**Data Source Class**";

    private SQLiteDatabase database;
    private DatabaseHelper helper;

    private String[] allColumns = {"_id", "username", "password", "email", "gender", "height", "initialWeight",
    "goalweight", "currentWeight"};

    //constructor
     UsersDataSource(Context context) {
        helper = new DatabaseHelper(context);
    }

     void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

     void close() {
        helper.close();
    }

     User createUser(String username/*, String password*/){//shudhu username input nicchi for trial
        //User user = new User(email, password);//redundant
        ContentValues values = new ContentValues();
        values.put("username", username);
        //values.put("password", password);
        long id = database.insert("Users", null, values);//ERROR//
         //^ arguments : tableName, nullColumnHack, ContentValues
         //returns the row id of the newly created row
         Cursor cursor = null;
         User user = null;
         if(id != -1) {
             cursor = database.query("Users", allColumns, "_id  = " + id, null, null, null, null);
             cursor.moveToFirst();
             user = cursorToUser(cursor);//ERROR//SOLVED
             cursor.close();
         }else{
             Log.e(TAG, "id returned -1");
         }
        return user;
    }

     void deleteUser(User user){
        if (user != null) {
            long id = user.getId();
            Log.e(TAG, "user's id = "+String.valueOf(id));
            database.delete("Users", "_id =" + id, null);//delete the given id (matching with the "_id" column) from the Users table
        }else{
            Log.e(TAG, "user argument is null");
        }
     }

     void deleteUser(String username){
         if (!username.equals("")) {
             User user = getUser(username);
             long id = user.getId();
             Log.e(TAG, "user's id = "+String.valueOf(id));
             database.delete("Users", "_id =" + id, null);//delete the given id (matching with the "_id" column) from the Users table
         }else{
             Log.e(TAG, "user argument is null")    ;
         }
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
        cursor.close();
        return users;
    }

    List<String> getAllUsernames(){
        List<String> usernames = new ArrayList<String>();
        Cursor cursor = database.query("Users", allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            String username = user.username;
            usernames.add(username);
            cursor.moveToNext();
        }
        cursor.close();
        return usernames;
    }


    User getUser(String username){
        Cursor cursor = database.query("Users", allColumns,"username = ?", new String[] {username}, null, null, null );
        cursor.moveToFirst();
        User user = null;
        while (!cursor.isAfterLast()) {
            user = cursorToUser(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return  user;
    }

    private User cursorToUser(Cursor cursor){
        User user = new User();
        user.id = cursor.getLong(0);//ERROR//GONE but dont know why
        user.username = cursor.getString(1);
        return user;
    }

}
