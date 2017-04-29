package com.example.user.sqlitedemo;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    private static final String TAG = "**MainActivity**";
    public UsersDataSource dataSource;
    int id = 0;
    ListView userslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userslist = (ListView) findViewById(R.id.list);

        dataSource = new UsersDataSource(this);
        dataSource.open();

        List<User> values = dataSource.getAllUsers();
        List<String> usernames = dataSource.getAllUsernames();
        //ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, values);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usernames);
        userslist.setAdapter(adapter);
    }

    public void onClick(View v){
        @SuppressWarnings("unchecked")
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) userslist.getAdapter();

        User user = null;
        /*User sharna = null;
        User fariha = null;
        User mahir = null;
        User apon = null;
        User rumman = null;*/
        switch (v.getId()){
            case R.id.addButton:
                String[] users = new String[] {"Sama", "Sharna", "Fariha", "Mahir", "Apon", "Rumman"};
                int nextInt = new Random().nextInt(5);
                user = dataSource.createUser(users[nextInt]);//ERROR////ERROR// --RESOLVED--
                /*sharna = dataSource.createUser("sharna@hotmail.com", "456");
                fariha = dataSource.createUser("fariha@yahoo.com", "789");
                mahir = dataSource.createUser("mahir@yahoo.com", "abcd");
                apon = dataSource.createUser("apon@hotmail.com", "efgh");
                rumman = dataSource.createUser("rumman@gmail.com", "ijkl");*/
                if (user != null) {
                    adapter.add(user.username);
                }else{
                    Log.e(TAG, "create User returns null");
                }
                /*adapter.add(sharna);
                adapter.add(fariha);
                adapter.add(mahir);
                adapter.add(apon);
                adapter.add(rumman);*/
                break;
            case R.id.deleteButton:
                if(userslist.getAdapter().getCount() > 0){
                    int n = userslist.getAdapter().getCount();
                    Log.e(TAG, "adapter's get Count returned = "+String.valueOf(n) );
                    //User tempUser = (User) userslist.getAdapter().getItem(id);//ERROR
                    if(id>=n)
                        id = 0;
                    String name = (String) userslist.getAdapter().getItem(id);//IndexOutOfBoundsException: Invalid index 5, size is 5 - RESOLVED BY THE ABOVE IF CONDITION
                    //String username = tempUser.username;
                    Log.e(TAG, name);
                    //name-er shathe match kore user object-ta nite hobe
                    user = dataSource.getUser(name);
                    id  = new Random().nextInt(n);
                    if(user!=null) {
                        dataSource.deleteUser(user);
                        adapter.remove(user.username);
                    } else {
                        Log.e(TAG, "getUser returned null");
                    }
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
