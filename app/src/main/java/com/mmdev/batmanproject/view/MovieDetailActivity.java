package com.mmdev.batmanproject.view;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.mmdev.batmanproject.R;
import com.mmdev.batmanproject.model.MovieDetail;
import com.mmdev.batmanproject.persistence.MovieDetailData;
import com.mmdev.batmanproject.util.Resource;
import com.mmdev.batmanproject.viewmodel.DetailViewModel;
import com.mmdev.batmanproject.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MovieDetailActivity extends DaggerAppCompatActivity {

    private static final String TAG = "MovieDetailActivity";

    @Inject
    ViewModelFactory factory;
    @Inject
    DetailViewModel viewModel;
    @Inject
    RequestManager requestManager;

    private FadableImageView imgDetailCover ;
    private ImageView imgDetailPoster;
    private TextView txtDetailTitle, txtDetailRelease, txtDetailDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imgDetailCover = findViewById(R.id.imgDetailCover);
        imgDetailPoster = findViewById(R.id.imgDetailPoster);
        txtDetailTitle = findViewById(R.id.txtDetailTitle);
        txtDetailRelease = findViewById(R.id.txtDetailRelease);
        txtDetailDuration = findViewById(R.id.txtDetailDuration);

        imgDetailCover.setEdgeLength(90);

        Bundle bundle = getIntent().getExtras();
        String imdbId = null;
        if (bundle != null){
            imdbId = bundle.getString("imdbId");
        }

        viewModel = ViewModelProviders.of(this,factory).get(DetailViewModel.class);

        subscribeObservers(imdbId);
    }

    private void subscribeObservers(String imdbId){

        viewModel.getDatabaseMovieDetail(imdbId).observe(this, new Observer<MovieDetailData>() {
            @Override
            public void onChanged(MovieDetailData movieDetailData) {
                if (movieDetailData != null){
                    requestManager.load(movieDetailData.getPoster()).into(imgDetailCover);
                    requestManager.load(movieDetailData.getPoster()).into(imgDetailPoster);
                    txtDetailTitle.setText(movieDetailData.getTitle());
                    txtDetailRelease.setText(movieDetailData.getReleased());
                    txtDetailDuration.setText("- " + movieDetailData.getRuntime());
                }
            }
        });

        viewModel.getResourceMovieDetail(imdbId).observe(this, new Observer<Resource<MovieDetail>>() {
            @Override
            public void onChanged(Resource<MovieDetail> movieDetailResource) {
                if (movieDetailResource != null) {
                    switch (movieDetailResource.status){
                        case LOADING:

                            break;

                        case ERROR:

                            break;

                        case SUCCESS:
                            Log.e(TAG, "Resource:   " + movieDetailResource.data.getImdbId()  );
                            break;
                    }
                }
            }
        });
    }
}