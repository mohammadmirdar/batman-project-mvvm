package com.mmdev.batmanproject.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;

import com.mmdev.batmanproject.model.Movie;
import com.mmdev.batmanproject.persistence.MovieDao;
import com.mmdev.batmanproject.persistence.MovieData;
import com.mmdev.batmanproject.remote.MovieApi;
import com.mmdev.batmanproject.util.Constants;
import com.mmdev.batmanproject.util.Resource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * this class uses for provide and manage fetch data from Web or Database.
 *
 * @author Mohammad Mirdar
 * @version 1.0
 * @since 2020-09-28
 */
public class MainRepository {
    private static final String TAG = "MainRepository";
    @Inject
    MovieApi movieApi;
    @Inject
    MovieDao movieDao;

    private Application application;

    private MediatorLiveData<Resource<Movie>> batmanMediator = new MediatorLiveData<>();

    @Inject
    public MainRepository(Application application) {
        this.application = application;

    }

    /**
     * This method used for observe state of webservice connection and
     * used for showing progress of fetching data from the web.
     *
     * @return LiveData that contains a Resource class of Batman model for different
     * stats of response.
     */
    public LiveData<Resource<Movie>> getAllBatmanMovies() {
        batmanMediator.setValue(Resource.loading(null));

        final LiveData<Resource<Movie>> source = LiveDataReactiveStreams.fromPublisher(

                movieApi.getAllBatmanList(Constants.API_KEY, Constants.SEARCH_NAME)
                        .onErrorReturn(throwable -> {
                            Movie movie = new Movie();
                            movie.setSearch(null);
                            Log.e(TAG, "getAllBatmanMovies: " + throwable.getMessage());
                            return movie;
                        })
                        .map((Function<Movie, Resource<Movie>>) movie -> {
                            if (movie.getSearch() == null) {
                                return Resource.error("Something went wrong", null);
                            }
                            movieDao.deleteAllBatmanList()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new SingleObserver<Integer>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onSuccess(Integer integer) {

                                            for (int i = 0; i < movie.getSearch().size(); i++) {
                                                Movie.Search search = movie.getSearch().get(i);

                                                MovieData movieData = new MovieData();
                                                movieData.setTitle(search.getTitle());
                                                movieData.setYear(search.getYear());
                                                movieData.setImdbId(search.getImdbId());
                                                movieData.setType(search.getType());
                                                movieData.setPoster_url(search.getPoster());

                                                insertMovieData(movieData);
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }
                                    });

                            return Resource.success(movie);
                        })
                        .subscribeOn(Schedulers.io())
        );

        batmanMediator.addSource(source, batmanResource -> {
            batmanMediator.setValue(batmanResource);
            batmanMediator.removeSource(source);
        });

        return batmanMediator;
    }

    private void insertMovieData(MovieData movieData) {
        movieDao.insertBatmanMovie(movieData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    /**
     * This method returns batman movies list from database
     * and will update from webservice.
     *
     * @return a LiveData that will observe in main activity
     */
    public LiveData<List<MovieData>> getAllDatabaseMovies() {
        return movieDao.getAllBatmanList();
    }


}
