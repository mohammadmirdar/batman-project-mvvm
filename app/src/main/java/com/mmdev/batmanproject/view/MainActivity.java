package com.mmdev.batmanproject.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.mmdev.batmanproject.R;
import com.mmdev.batmanproject.adapter.MovieListAdapter;
import com.mmdev.batmanproject.model.Batman;
import com.mmdev.batmanproject.persistence.BatmanData;
import com.mmdev.batmanproject.util.Resource;
import com.mmdev.batmanproject.viewmodel.MainViewModel;
import com.mmdev.batmanproject.viewmodel.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG = "MainActivity";
    @Inject
    MainViewModel mainViewModel;
    @Inject
    MovieListAdapter adapter;
    @Inject
    ViewModelFactory factory;

    private RecyclerView movieRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieRecycler = findViewById(R.id.moviesRecycler);
        mainViewModel = ViewModelProviders.of(this,factory).get(MainViewModel.class);

        initRecycler();
        subscribeObservers();
    }

    private void initRecycler(){
        movieRecycler.setLayoutManager(new GridLayoutManager(this,3,RecyclerView.VERTICAL,false));
        movieRecycler.setHasFixedSize(true);
        movieRecycler.setAdapter(adapter);
    }
    private void subscribeObservers(){

        mainViewModel.getResourceLiveData().observe(this, new Observer<Resource<Batman>>() {
            @Override
            public void onChanged(Resource<Batman> batmanResource) {
                Log.e(TAG, "onChanged: " + batmanResource.data );
            }
        });

        mainViewModel.getBatmanLiveData().observe(this, new Observer<List<BatmanData>>() {
            @Override
            public void onChanged(List<BatmanData> batmanData) {
                if (batmanData != null) {
                    adapter.setBatmanData(batmanData);
                }
            }
        });


    }
}