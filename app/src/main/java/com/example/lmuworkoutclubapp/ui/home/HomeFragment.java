package com.example.lmuworkoutclubapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lmuworkoutclubapp.HomeActivity;
import com.example.lmuworkoutclubapp.LoginActivity;
import com.example.lmuworkoutclubapp.R;
import com.example.lmuworkoutclubapp.databinding.FragmentHomeBinding;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private String Username,password, UserRole;;
    SharedPreferences sharedpreferences;
    public static final String SHARED_PREFS = "shared_prefs";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();







        sharedpreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        Username = sharedpreferences.getString("Username", null);
        password = sharedpreferences.getString("Password", null);
        UserRole = sharedpreferences.getString("UserRole", null);




        if (Username != null) {

            binding.Username.setText(Username);
        }

        String[] dummyAttendance =  {"Cardio Work out on " + Calendar.getInstance().getTime().toString(),"Cardio Work out on " + Calendar.getInstance().getTime().toString(),
                "Cardio Work out on " + Calendar.getInstance().getTime().toString(),
                "Cardio Work out on " + Calendar.getInstance().getTime().toString(),
                "Cardio Work out on " + Calendar.getInstance().getTime().toString(),
                "Cardio Work out on " + Calendar.getInstance().getTime().toString(),
                "Cardio Work out on " + Calendar.getInstance().getTime().toString(),};

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.list_text_view, dummyAttendance);

        binding.listview.setAdapter(adapter);
      //  binding.UserCount.setText(Integer.toString(listOfUsers.getCount()));
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
                startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));




            }
        });
    //    Intent intent =  getArguments().getParcelable("Username");

      //  String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
  //    binding.welcome.setText( intent.getParcelableExtra("Username"));
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
           public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}