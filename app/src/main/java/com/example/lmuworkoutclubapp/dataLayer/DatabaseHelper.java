package com.example.lmuworkoutclubapp.dataLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lmuworkoutclubapp.Models.Attendance;
import com.example.lmuworkoutclubapp.Models.User;
import com.example.lmuworkoutclubapp.Models.Workout;

import java.util.Calendar;

public class DatabaseHelper  extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "LMUWorkoutAppDb.db";

    public static final String UsersTable = "Users";
    public static final String Col_userID = "ID";
    public static final String COL_firstName = "FirstName";
    public static final String COL_lastName = "LastName";
    public static final String COL_userName = "UserName";
    public static final String COL_Email = "Email";
    public static final String COL_Password = "Password";
    public static final String COL_WorkOutType = "WorkOutType";
    public static final String COL_userRole = "UserRole";
    public static final String COL_DateCreated = "DateCreated";



    public static final String WorkoutTable = "Workouts";
    public static final String COL_workOutID = "ID";
    public static final String COL_workOutName = "WorkOutName";
    public static final String COL_workOutObjectives = "WorkOutObjectives";
    public static final String COL_workOutDateCreated = "DateCreated";



    public static final String Attendance = "Attendance";
    public static final String COL_attendanceID = "ID";
    public static final String COL_AttendanceuserName = "UserName";
    public static final String COL_attendanceDate = "DateCreated";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + UsersTable + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COL_firstName + " TEXT, "
                + COL_lastName +" TEXT, " + COL_userName +" TEXT, " + COL_Email + " TEXT, "
                + COL_Password + " TEXT, " + COL_WorkOutType + " TEXT, " + COL_userRole + " TEXT,  " + COL_DateCreated + " DATE  )");


        db.execSQL("create table " + WorkoutTable + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COL_workOutName + " TEXT, "
                + COL_workOutObjectives +" TEXT, " + COL_workOutDateCreated +" DATE )");



        db.execSQL("create table " + Attendance + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COL_AttendanceuserName + " TEXT, "
                  + COL_attendanceDate + " DATE  )");
        seedDatabasewithAdmin();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UsersTable);
        db.execSQL("DROP TABLE IF EXISTS " + WorkoutTable);
        db.execSQL("DROP TABLE IF EXISTS " + Attendance);

        onCreate(db);
        seedDatabasewithAdmin();
    }

    public void seedDatabasewithAdmin(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_firstName, "Admin");
        contentValues.put(COL_lastName, "Admin");
        contentValues.put(COL_userName, "Admin");
        contentValues.put(COL_Email, "Admin@Admin.com");
        contentValues.put(COL_Password, "Admin@123");
        contentValues.put(COL_WorkOutType, "Strength");
        contentValues.put(COL_userRole, "Admin");
        contentValues.put(COL_DateCreated, String.valueOf(Calendar.getInstance().getTime()));
        long result = db.insert(UsersTable, null, contentValues);
    }

   // public boolean insertUser(String firstName, String lastName, String userName, String Email, String Password, String workOutType, String userRole)
    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_firstName, user.firstName);
        contentValues.put(COL_lastName, user.lastName);
        contentValues.put(COL_userName, user.userName);
        contentValues.put(COL_Email, user.Email);
        contentValues.put(COL_Password, user.Password);
        contentValues.put(COL_WorkOutType, user.workOutType);
        contentValues.put(COL_userRole, user.userRole);
        contentValues.put(COL_DateCreated, String.valueOf(Calendar.getInstance().getTime()));


        long result = db.insert(UsersTable, null, contentValues);
        if (result == -1){
            return false;}
        else {
            return true;
    }

    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + UsersTable, null);

        return res;
//
//        String[] columns = {
//                COL_userRole
//        };
//        SQLiteDatabase db = this.getReadableDatabase();
//        // selection criteria
//        String selection = COL_userRole + " != ?";
//        // selection argument
//        String[] selectionArgs = {"Admin"};
//        // query user table with condition
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
//         */
//        Cursor cursor = db.query(UsersTable, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                      //filter by row groups
//                null);                      //The sort order
//
       // return cursor;

    }



    public boolean checkAUserExists(String Username) {
        // array of columns to fetch
        String[] columns = {
                COL_userName
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_userName + " = ?";
        // selection argument
        String[] selectionArgs = {Username};
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(UsersTable, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
       if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    public Cursor getAUserfromDb(String Username) {
        // array of columns to fetch
        String[] columns = {
                COL_userName
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_userName + " = ?";
        // selection argument
        String[] selectionArgs = {Username};
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(UsersTable, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
        if (cursorCount > 0) {
            return cursor;
        }
        return null;
    }



    public boolean checkLoginUser(String Username, String password) {
        // array of columns to fetch
        String[] columns = {
                Col_userID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_userName + " = ?" + " AND " + COL_Password+ " = ?";
        // selection arguments
        String[] selectionArgs = {Username, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(UsersTable, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }





    public boolean insertWorkout(Workout workout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_workOutName, workout.workoutName);
        contentValues.put(COL_DateCreated, String.valueOf(Calendar.getInstance().getTime()));


        long result = db.insert(WorkoutTable, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getAllWorkOuts() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + WorkoutTable, null);
        return res;
    }





    public boolean checkWorkoutExists(String workoutname) {
        // array of columns to fetch
        String[] columns = {
                COL_workOutID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_workOutName + " = ?";
        // selection argument
        String[] selectionArgs = {workoutname};
        // query user table with condition

        Cursor cursor = db.query(WorkoutTable, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }













    public boolean insertAttendance(Attendance attendance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_workOutName, attendance.UserID);
        contentValues.put(COL_DateCreated, String.valueOf(Calendar.getInstance().getTime()));


        long result = db.insert(WorkoutTable, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getAllAttendance() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Attendance, null);
        return res;
    }




//
//    public boolean updateData(String id, String name, String surname, String marks) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1, id);
//        contentValues.put(COL_2, name);
//        contentValues.put(COL_3, surname);
//        contentValues.put(COL_4, marks);
//        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
//        return true;
//    }

//    public Integer deleteData(String id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
//    }

}