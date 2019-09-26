package com.wht.mvvmtest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.wht.mvvmtest.R;
import com.wht.mvvmtest.databinding.ActivityMainBinding;
import com.wht.mvvmtest.model.People;
import com.wht.mvvmtest.viewModel.PeopleViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        PeopleViewModel peopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);
        peopleViewModel.init();


        activityMainBinding.setData(peopleViewModel);
        activityMainBinding.setLifecycleOwner(this);
        setupListPeopleView(activityMainBinding.listPeople);
        //如果在ViewModel中 peopleList更新了 就会走这个方法
        peopleViewModel.getPeopleList().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(List<People> people) {
                Log.e(TAG, "onChanged: ");
                updata(people);
            }
        });


    }

    private void setupListPeopleView(RecyclerView listPeople) {
        PeopleAdapert adapter = new PeopleAdapert();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    private void updata(List<People> people) {
        PeopleAdapert peopleAdapter = (PeopleAdapert) activityMainBinding.listPeople.getAdapter();
        peopleAdapter.setPeopleList(people);
    }
}

