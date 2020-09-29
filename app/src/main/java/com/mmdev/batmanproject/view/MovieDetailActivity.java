package com.mmdev.batmanproject.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
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

    private FadableImageView imgDetailCover;
    private ImageView imgDetailPoster;
    private TextView txtDetailTitle, txtDetailRelease, txtDetailDuration, txtVote,
            txtVoteCount, txtPlot, txtGenre, txtWriter, txtDirector, txtActors,
            txtLanguage, txtCountry, txtAwards, txtReleased, txtRuntime;
    protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imgDetailCover = findViewById(R.id.imgDetailCover);
        imgDetailPoster = findViewById(R.id.imgDetailPoster);
        txtDetailTitle = findViewById(R.id.txtDetailTitle);
        txtDetailRelease = findViewById(R.id.txtDetailRelease);
        txtDetailDuration = findViewById(R.id.txtDetailDuration);
        txtVote = findViewById(R.id.txtVote);
        txtVoteCount = findViewById(R.id.txtVoteCount);
        txtPlot = findViewById(R.id.txtPlot);
        txtGenre = findViewById(R.id.txtGenre);
        txtWriter = findViewById(R.id.txtWriter);
        txtDirector = findViewById(R.id.txtDirector);
        txtActors = findViewById(R.id.txtActors);
        txtLanguage = findViewById(R.id.txtLanguage);
        txtCountry = findViewById(R.id.txtCountry);
        txtAwards = findViewById(R.id.txtAwards);
        txtReleased = findViewById(R.id.txtReleased);
        txtRuntime = findViewById(R.id.txtRuntime);
        progressBar = findViewById(R.id.detailProgress);

        imgDetailCover.setEdgeLength(90);

        ;
        Bundle bundle = getIntent().getExtras();
        String imdbId = null;
        if (bundle != null) {
            imdbId = bundle.getString("imdbId");
        }

        viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel.class);

        subscribeObservers(imdbId);
    }

    private void subscribeObservers(String imdbId) {

        viewModel.getDatabaseMovieDetail(imdbId).observe(this, new Observer<MovieDetailData>() {
            @Override
            public void onChanged(MovieDetailData movieDetailData) {
                if (movieDetailData != null) {
                    requestManager.load(movieDetailData.getPoster()).into(imgDetailCover);
                    requestManager.load(movieDetailData.getPoster()).into(imgDetailPoster);
                    txtDetailTitle.setText(movieDetailData.getTitle());
                    txtDetailRelease.setText(movieDetailData.getReleased());
                    txtDetailDuration.setText("- " + movieDetailData.getRuntime());
                    txtVote.setText(movieDetailData.getImdbRating());
                    txtVoteCount.setText(movieDetailData.getImdbVotes());
                    txtPlot.setText(movieDetailData.getPlot());
                    txtGenre.setText(movieDetailData.getGenre());
                    txtWriter.setText(movieDetailData.getWriter());
                    txtDirector.setText(movieDetailData.getDirector());
                    txtActors.setText(movieDetailData.getActors());
                    txtLanguage.setText(movieDetailData.getLanguage());
                    txtCountry.setText(movieDetailData.getCountry());
                    txtAwards.setText(movieDetailData.getAwards());
                    txtReleased.setText(movieDetailData.getReleased());
                    txtRuntime.setText(movieDetailData.getRuntime());
                }
            }
        });

        viewModel.getResourceMovieDetail(imdbId).observe(this, new Observer<Resource<MovieDetail>>() {
            @Override
            public void onChanged(Resource<MovieDetail> movieDetailResource) {
                if (movieDetailResource != null) {
                    switch (movieDetailResource.status) {
                        case LOADING:
                            showProgressBar(true);
                            break;

                        case ERROR:
                            showProgressBar(false);
                            break;

                        case SUCCESS:
                            Log.e(TAG, "Resource:   " + movieDetailResource.data.getImdbId());
                            showProgressBar(false);
                            break;
                    }
                }
            }
        });
    }
    private void showProgressBar(boolean isShowing){
        if (isShowing){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}