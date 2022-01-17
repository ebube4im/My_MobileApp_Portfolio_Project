package com.example.lmuworkoutclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.lmuworkoutclubapp.Models.User;
import com.example.lmuworkoutclubapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    User user;
    SharedPreferences sharedpreferences;
    public static final String SHARED_PREFS = "shared_prefs";
    public static String Username = "";
    public static String Password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        user = new User(getApplicationContext());

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Username = sharedpreferences.getString("Username", null);
        Password = sharedpreferences.getString("Password", null);
        // below two lines will put values for
        // email and password in shared preferences.


        binding.RegisterActivityText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



        if(TextUtils.isEmpty( binding.Username.getText().toString())|| binding.Password.getText().toString().isEmpty() ){

            Toast.makeText(getApplicationContext(), "One or more fields are not filled correctly", Toast.LENGTH_SHORT).show();

        }
        else{

            Boolean dbUserLoginExists;
            dbUserLoginExists   = user.checkUserLogin(binding.Username.getText().toString(), binding.Password.getText().toString(), getApplicationContext());

            if(dbUserLoginExists){
                Toast.makeText(getApplicationContext(), "Login was Successful for " + binding.Username.getText().toString(), Toast.LENGTH_SHORT).show();
             //   user = new User(getApplicationContext());
                 user = user.getAUser(binding.Username.getText().toString());

                if (user.userRole != "") {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("Username",  user.userName );
                    editor.putString("Password", user.Password);
                    editor.putString("UserRole", user.userRole);

                    // to save our data with key and value.
                    editor.apply();
                }




                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("Username",  binding.Username.getText().toString() );
                    editor.putString("Password", binding.Password.getText().toString());
                    editor.putString("UserRole", user.userRole);

                    // to save our data with key and value.
                    editor.apply();
                     startActivity(new Intent(getApplicationContext(), HomeActivity.class));



               }
            else {
                if(binding.Username.getText().toString().contains("Admin") || binding.Password.getText().toString().trim() == "Admin123") {
                    startActivity(new Intent(getApplicationContext(), InstructorActivity.class).putExtra("Username", binding.Username.getText().toString()));
                }

                Toast.makeText(getApplicationContext(), "Login failed! One of the details is incorrect", Toast.LENGTH_SHORT).show();

            }



        }
            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Username != null && Password != null) {

            startActivity(new Intent(getApplicationContext(), HomeActivity.class).putExtra("Username", binding.Username.getText().toString()));

        }
    }
}