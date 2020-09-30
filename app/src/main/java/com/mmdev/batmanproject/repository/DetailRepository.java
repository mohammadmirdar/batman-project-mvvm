package com.mmdev.batmanproject.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.mmdev.batmanproject.model.MovieDetail;
import com.mmdev.batmanproject.persistence.MovieDao;
import com.mmdev.batmanproject.persistence.MovieDetailData;
import com.mmdev.batmanproject.remote.MovieApi;
import com.mmdev.batmanproject.util.Constants;
import com.mmdev.batmanproject.util.Resource;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * this class uses for provide and manage fetch of data from Web or Database.
 *
 * @author Mohammad Mirdar
 * @version 1.0
 * @since 2020-09-28
 */
public class DetailRepository {

    private static final String TAG = "DetailRepository";
    @Inject
    MovieApi movieApi;
    @Inject
    MovieDao movieDao;
    @Inject
    Application application;
    private MediatorLiveData<Resource<MovieDetail>> mediatorLiveData = new MediatorLiveData<>();

    @Inject
    public DetailRepository() {

    }

    public LiveData<MovieDetailData> getDatabaseMovieDetail(String movieId) {
        return movieDao.getMovieById(movieId);
    }

    /**
     * Gets data from Webservice and convert it to a MutableLiveData that
     * its Generic class is Resource<MovieDetail> that used for managing response status.
     * @param movieId is String parameter to pass it to Webservice.
     */
    public LiveData<Resource<MovieDetail>> getRemoteMovieDetail(String movieId) {
        mediatorLiveData.setValue(Resource.loading(null));

        final LiveData<Resource<MovieDetail>> source = LiveDataReactiveStreams.fromPublisher(
                movieApi.getMovieDetail(Constants.API_KEY, movieId)
                        .onErrorReturn(new Function<Throwable, MovieDetail>() {
                            @Override
                            public MovieDetail apply(Throwable throwable) throws Exception {
                                Log.e(TAG, "apply: " + throwable.getMessage());
                                MovieDetail movieDetail = new MovieDetail();
                                movieDetail.setImdbId(".");
                                return movieDetail;
                            }
                        })
                        .map(new Function<MovieDetail, Resource<MovieDetail>>() {
                            @Override
                            public Resource apply(MovieDetail movieDetail) throws Exception {
                                if (movieDetail.getImdbId().equals(".")) {
                                    return Resource.error("Something went wrong", null);
                                }
                                insertMovieDetailToDatabase(fillMovieDetailData(movieDetail));
                                return Resource.success(movieDetail);
                            }
                        })
                        .subscribeOn(Schedulers.io()));

        mediatorLiveData.addSource(source, new Observer<Resource<MovieDetail>>() {
            @Override
            public void onChanged(Resource<MovieDetail> movieDetailResource) {
                mediatorLiveData.setValue(movieDetailResource);
                mediatorLiveData.removeSource(source);
            }
        });
        return mediatorLiveData;
    }

    private void insertMovieDetailToDatabase(MovieDetailData movieDetail) {
        movieDao.insertMovieDetail(movieDetail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Long aLong) {
                        Log.e(TAG, "onSuccess: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private MovieDetailData fillMovieDetailData(MovieDetail m) {
        MovieDetailData movieDetailData = new MovieDetailData();

        movieDetailData.setActors(m.getActors());
        movieDetailData.setAwards(m.getAwards());
        movieDetailData.setCountry(m.getCountry());
        movieDetailData.setDirector(m.getDirector());
        movieDetailData.setGenre(m.getGenre());
        movieDetailData.setImdbId(m.getImdbId());
        movieDetailData.setImdbRating(m.getImdbRating());
        movieDetailData.setImdbVotes(m.getImdbVotes());
        movieDetailData.setLanguage(m.getLanguage());
        movieDetailData.setPlot(m.getPlot());
        movieDetailData.setPoster(m.getPoster());
        movieDetailData.setProduction(m.getProduction());
        movieDetailData.setRated(m.getRated());
        movieDetailData.setReleased(m.getReleased());
        movieDetailData.setRuntime(m.getRuntime());
        movieDetailData.setTitle(m.getTitle());
        movieDetailData.setType(m.getType());
        movieDetailData.setWriter(m.getWriter());
        movieDetailData.setYear(m.getYear());

        return movieDetailData;
    }
}
