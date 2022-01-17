package com.example.lmuworkoutclubapp.Models;

import android.content.Context;
import android.database.Cursor;

import com.example.lmuworkoutclubapp.dataLayer.DatabaseHelper;

public class Workout {

    int ID;
   public String workoutName;
   public String workoutObjectives;
    private final DatabaseHelper db;

    public Workout(Context context) {
        db = new DatabaseHelper(context);
    }

   public String CreateWorkOut(Workout workout){

        if (checkWorkoutExists(workout.workoutName)){
           return "Work out already exists";
       }
       boolean result = db.insertWorkout(workout);

       if (result) {
           return "Workout " + workout.workoutName + " Created Successfully";
       } else {
           return "An error occurred while Creating user";
       }


   }

    private boolean checkWorkoutExists(String workoutName) {

        Boolean result = db.checkAUserExists(workoutName);

        return result;
    }




    public Cursor getAllWorkouts() {
        Cursor res = db.getAllWorkOuts();
        if (res.getCount() == 0) {
            // show message

            return null;
        }
        else
        {
            return res;
        }


    }


}
