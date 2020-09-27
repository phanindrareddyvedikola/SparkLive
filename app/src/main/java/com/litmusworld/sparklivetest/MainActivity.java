package com.litmusworld.sparklivetest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.litmusworld.sparklivetest.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ActivityMainBinding binding;
    private  UserAdapter userAdapter;
    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        View view =binding.getRoot() ;
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.init();

        userViewModel.getUserData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {

                userAdapter.notifyDataSetChanged();

            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){

        userAdapter = new UserAdapter(this,userViewModel.getUserData().getValue());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        binding.recyclerview.setLayoutManager(layoutManager);

        binding.recyclerview.setAdapter(userAdapter);

    }


}