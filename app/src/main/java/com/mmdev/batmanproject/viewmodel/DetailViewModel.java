package com.mmdev.batmanproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mmdev.batmanproject.model.MovieDetail;
import com.mmdev.batmanproject.persistence.MovieDetailData;
import com.mmdev.batmanproject.repository.DetailRepository;
import com.mmdev.batmanproject.util.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DetailViewModel extends ViewModel {

    private DetailRepository repository;

    @Inject
    public DetailViewModel(DetailRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<MovieDetail>> getResourceMovieDetail(String imdbId) {
        return repository.getRemoteMovieDetail(imdbId);
    }

    public LiveData<MovieDetailData> getDatabaseMovieDetail(String imdbId) {
        return repository.getDatabaseMovieDetail(imdbId);
    }
}
