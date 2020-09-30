package com.mmdev.batmanproject.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mmdev.batmanproject.R;
import com.mmdev.batmanproject.adapter.MovieListAdapter;
import com.mmdev.batmanproject.model.Movie;
import com.mmdev.batmanproject.persistence.MovieData;
import com.mmdev.batmanproject.util.GridSpacingItemDecoration;
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
    private ProgressBar mainProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieRecycler = findViewById(R.id.moviesRecycler);
        mainProgress = findViewById(R.id.mainProgress);

        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        Drawable drawable = getResources().getDrawable(R.drawable.batman_eyes);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(drawable);
            getSupportActionBar().setTitle("");
        }

        initRecycler();
        subscribeObservers();
    }

    private void initRecycler() {
        movieRecycler.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));
        movieRecycler.setHasFixedSize(true);
        movieRecycler.addItemDecoration(new GridSpacingItemDecoration(3, getResources().getDimensionPixelSize(R.dimen.recycler_view_item_width)));
        movieRecycler.setAdapter(adapter);
    }

    private void subscribeObservers() {

        mainViewModel.getResourceLiveData().observe(this, new Observer<Resource<Movie>>() {
            @Override
            public void onChanged(Resource<Movie> batmanResource) {
                if (batmanResource != null) {
                    switch (batmanResource.status) {
                        case SUCCESS:
                        case ERROR:
                            showProgressBar(false);
                            showRecyclerView(true);
                            break;
                        case LOADING:
                            showProgressBar(true);
                            break;
                    }
                }
            }
        });

        mainViewModel.getBatmanLiveData().observe(this, new Observer<List<MovieData>>() {
            @Override
            public void onChanged(List<MovieData> movieData) {
                if (movieData != null) {
                    adapter.setMovieData(movieData);
                }
            }
        });

    }

    private void showProgressBar(boolean isShowing) {
        if (isShowing) {
            mainProgress.setVisibility(View.VISIBLE);
        } else {
            mainProgress.setVisibility(View.GONE);
        }
    }

    private void showRecyclerView(boolean isShowing) {
        if (isShowing) {
            movieRecycler.setVisibility(View.VISIBLE);
        } else {
            movieRecycler.setVisibility(View.GONE);
        }
    }
}