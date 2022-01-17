package com.example.lmuworkoutclubapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmuworkoutclubapp.Models.User;
import com.example.lmuworkoutclubapp.databinding.ActivityMainBinding;
import com.example.lmuworkoutclubapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityRegisterBinding binding;

    private User user;
    String[] workOutTypes = { "Select Workout Type","Cardiovascular Aerobic", "Strength", "Stretching"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // this.getActionBar().hide();
        user = new User(getApplicationContext());
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         // A Spinner Dropdwon to show the Work out Types
        Spinner workOutTypeList = binding.spinner;
        workOutTypeList.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                workOutTypes);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        workOutTypeList.setAdapter(ad);


        binding.LoginActivityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        binding.CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty( binding.firstName.getText().toString())|| binding.lastName.getText().toString().isEmpty()  ||
                        binding.Username.getText().toString().isEmpty()
                || binding.Password.getText().toString().isEmpty() || binding.Email.getText().toString().isEmpty() ){

                    Toast.makeText(getApplicationContext(), "One or more fields are not filled correctly", Toast.LENGTH_SHORT).show();


                } else  if (binding.Password.length() < 6){
                    binding.Password.setError("Password too Short!");
                } else if (user.workOutType == "Select Workout Type"){
                    ((TextView) workOutTypeList.getSelectedView()).setError("Work out type is required!");
                }

//                else if (binding.confirmPassword.getText().toString().trim() != binding.Password.getText().toString().trim()){
//                    binding.confirmPassword.setError("Passwords do not Match");
//                }

                else {

                    user.firstName = binding.firstName.getText().toString();
                    user.lastName = binding.lastName.getText().toString();
                    user.userName = binding.Username.getText().toString();
                    user.Password = binding.Password.getText().toString();
                    user.Email = binding.Email.getText().toString();
                    user.userRole = "Student";

                    Toast.makeText(getApplicationContext(), "Button Was Clicked", Toast.LENGTH_SHORT).show();
                     String result = user.createUser( user);

                     if (result == "User Exists"){
                         binding.Username.setError("Already Exists! Choose a new one");
                         Toast.makeText(getApplicationContext(), " Oops! Student ID / Staff ID / Username already exists", Toast.LENGTH_SHORT).show();
//
                     }
                     if (!result.isEmpty()){
                         Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                      if (result.contains("Successfully")){
                          Toast.makeText(getApplicationContext(), "Login with your new account details", Toast.LENGTH_LONG).show();
                          startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                      }
//                         AlertDialog.Builder builder;
//                         builder = new AlertDialog.Builder(RegisterActivity.this);
//                         builder.setCancelable(true);
//                         builder.setTitle("Users");
//                         builder.setMessage(user.getAllUsers());
//                         builder.show();
                     }


                }

                }
        });
//
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    });
//       // setContentView(R.layout.activity_register);
//
//    }
}

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        user.workOutType = workOutTypes[i].toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}






//
//// Main Activity implements Adapter view
//public class MainActivity
//        extends AppCompatActivity
//        implements AdapterView.OnItemSelectedListener {
//
//    // create array of Strings
//    // and store name of courses
//    String[] courses = { "C", "Data structures",
//            "Interview prep", "Algorithms",
//            "DSA with java", "OS" };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Take the instance of Spinner and
//        // apply OnItemSelectedListener on it which
//        // tells which item of spinner is clicked
//        Spinner spino = findViewById(R.id.coursesspinner);
//        spin.setOnItemSelectedListener(this);
//
//        // Create the instance of ArrayAdapter
//        // having the list of courses
//        ArrayAdapter ad
//                = new ArrayAdapter(
//                this,
//                android.R.layout.simple_spinner_item,
//                courses);
//
//        // set simple layout resource file
//        // for each item of spinner
//        ad.setDropDownViewResource(
//                android.R.layout
//                        .simple_spinner_dropdown_item);
//
//        // Set the ArrayAdapter (ad) data on the
//        // Spinner which binds data to spinner
//        spino.setAdapter(ad);
//    }
//
//    // Performing action when ItemSelected
//    // from spinner, Overriding onItemSelected method
//    @Override
//    public void onItemSelected(AdapterView<*> arg0,
//                               View arg1,
//                               int position,
//                               long id)
//    {
//
//        // make toastof name of course
//        // which is selected in spinner
//        Toast.makeText(getApplicationContext(),
//                courses[position],
//                Toast.LENGTH_LONG)
//                .show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<*> arg0)
//    {
//        // Auto-generated method stub
//    }
//}
