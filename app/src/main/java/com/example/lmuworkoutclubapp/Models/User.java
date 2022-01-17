package com.example.lmuworkoutclubapp.Models;

import android.content.Context;
import android.database.Cursor;

import com.example.lmuworkoutclubapp.dataLayer.DatabaseHelper;

import java.util.List;

public class User {

    int ID;
    public String firstName;
    public String lastName;
    public String userName;
    public String Password;
    public String Email;
    public String workOutType;
    public String userRole;
    public List<User> UserList;
    private final DatabaseHelper db;
    private final Context _context;

    public User(Context context) {
        db = new DatabaseHelper(context);
        _context = context;
    }
//
//    public User(){
////
//    }

    public String createUser(User user) {


        if (checkAUserExists(user.userName)){
            return "User Exists";
        }
        boolean result = db.insertUser(user);

        if (result) {
            return "User " + user.userName + " Created Successfully";
        } else {
            return "An error occurred while Creating user";
        }


    }

    public Cursor getAllUsers() {
        Cursor res = db.getAllUsers();
        if (res.getCount() == 0) {
            // show message

            return null;
        }
        else{
            return res;
        }



    }

    public boolean checkAUserExists(String Username) {
            Boolean result = db.checkAUserExists(Username);

            return result;
    }

    public User getAUser(String Username) {
        Cursor listOfUsers = db.getAUserfromDb(Username);
        User newuser = new User(_context);

        if(listOfUsers.getCount() > 0) {

            while (listOfUsers.isFirst()) {
                newuser.firstName = listOfUsers.getString(1);
                newuser.lastName = listOfUsers.getString(2);
                newuser.userName = listOfUsers.getString(3);
                newuser.Email = listOfUsers.getString(4);
                newuser.Password = listOfUsers.getString(5);
                newuser.workOutType = listOfUsers.getString(6);
                newuser.userRole = listOfUsers.getString(7);
            }
        }
        return newuser;
    }


    public Boolean checkUserLogin(String Username, String Password, Context context) {
        Boolean result = false;
        result = db.checkLoginUser(Username, Password);

        if (result == true){
            return true;
        }
      return false;
    }







}
