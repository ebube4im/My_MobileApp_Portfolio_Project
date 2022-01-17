package com.example.lmuworkoutclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ArrayAdapter;

import com.example.lmuworkoutclubapp.Models.User;
import com.example.lmuworkoutclubapp.databinding.ActivityInstructorBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructorActivity extends AppCompatActivity {

    public ActivityInstructorBinding binding;
    public List<User> userList;
    SharedPreferences sharedpreferences;
    private String Username,password, UserRole;;

    public static final String SHARED_PREFS = "shared_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInstructorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        Username = sharedpreferences.getString("Username", null);
        password = sharedpreferences.getString("Password", null);
        UserRole = sharedpreferences.getString("UserRole", null);



        User user = new User(getApplicationContext());
        ArrayList<User> userList = new ArrayList<>();

        Cursor listOfUsers = user.getAllUsers();
        //Array Users = new Array();
        String[] testArray = new String[listOfUsers.getCount()];
         listOfUsers = user.getAllUsers();
        int counter = 0;
        while (listOfUsers.moveToNext()) {

            testArray[counter] = listOfUsers.getString(1) + " " + listOfUsers.getString(2) ;
            counter++;
//           User newuser = new User(getApplicationContext());
//            newuser.firstName =  listOfUsers.getString(1);
//            newuser.lastName =   listOfUsers.getString(2);
//            newuser.userName  = listOfUsers.getString(3) ;
//            newuser.Email =  listOfUsers.getString(4) ;
//            newuser.Password = listOfUsers.getString(5) ;
//            newuser.workOutType = listOfUsers.getString(6) ;
//            newuser.userRole =   listOfUsers.getString(7) ;
//
//            userList.add(newuser);
        }



//        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME,
//                ContactsContract.CommonDataKinds.Phone.NUMBER};
//        int[] toViews = {R.id.display_name, R.id.phone_number};

        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_text_view, testArray);

         binding.listview.setAdapter(adapter);
         binding.UserCount.setText(Integer.toString(listOfUsers.getCount()));
         binding.Username.setText(Username);


        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();


                editor.remove("Username");
                editor.remove("Password");
                editor.remove("UserRole");

                // to save our data with key and value.
                editor.apply();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));




            }
        });



    }
}