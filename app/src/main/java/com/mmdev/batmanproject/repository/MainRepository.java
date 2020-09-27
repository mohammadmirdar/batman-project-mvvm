package com.mmdev.batmanproject.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;

import com.mmdev.batmanproject.model.Batman;
import com.mmdev.batmanproject.persistence.BatmanDao;
import com.mmdev.batmanproject.persistence.BatmanData;
import com.mmdev.batmanproject.persistence.MovieDatabase;
import com.mmdev.batmanproject.remote.BatmanApi;
import com.mmdev.batmanproject.util.Constants;
import com.mmdev.batmanproject.util.Resource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {
    private static final String TAG = "MainRepository";
    @Inject
    BatmanApi batmanApi;
    private BatmanDao batmanDao;
    private Application application;

    private MediatorLiveData<Resource<Batman>> batmanMediator = new MediatorLiveData<>();

    @Inject
    public MainRepository(Application application) {
        this.application = application;
        batmanDao = MovieDatabase.getInstance(application).batmanDao();
    }

    /**
     * This method used for observe state of webservice connection and
     * used for showing progress of fetching data from the web.
     *
     * @return LiveData that contains a Resource class of Batman model for different
     * stats of response.
     */
    public LiveData<Resource<Batman>> getAllBatmanMovies() {
        batmanMediator.setValue(Resource.loading(null));

        final LiveData<Resource<Batman>> source = LiveDataReactiveStreams.fromPublisher(

                batmanApi.getAllBatmanList(Constants.API_KEY, Constants.SEARCH_NAME)
                        .onErrorReturn(throwable -> {
                            Batman batman = new Batman();
                            batman.setSearch(null);
                            Log.e(TAG, "getAllBatmanMovies: " + throwable.getMessage());
                            return batman;
                        })
                        .map((Function<Batman, Resource<Batman>>) batman -> {
                            if (batman.getSearch() == null) {
                                return Resource.error("Something went wrong", null);
                            }
                            batmanDao.deleteAllBatmanList()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new SingleObserver<Integer>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onSuccess(Integer integer) {

                                            for (int i = 0; i < batman.getSearch().size(); i++) {
                                                Batman.Search search = batman.getSearch().get(i);

                                                BatmanData batmanData = new BatmanData();
                                                batmanData.setTitle(search.getTitle());
                                                batmanData.setYear(search.getYear());
                                                batmanData.setImdbId(search.getImdbId());
                                                batmanData.setType(search.getType());
                                                batmanData.setPoster_url(search.getPoster());

                                                insertMovieData(batmanData);
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }
                                    });

                            return Resource.success(batman);
                        })
                        .subscribeOn(Schedulers.io())
        );

        batmanMediator.addSource(source, batmanResource -> {
            batmanMediator.setValue(batmanResource);
            batmanMediator.removeSource(source);
        });

        return batmanMediator;
    }

    private void insertMovieData(BatmanData batmanData) {
        batmanDao.insertBatmanMovie(batmanData)
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
    public LiveData<List<BatmanData>> getAllDatabaseMovies() {
        return batmanDao.getAllBatmanList();
    }


}
