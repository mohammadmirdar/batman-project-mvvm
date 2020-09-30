package com.mmdev.batmanproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.mmdev.batmanproject.model.Movie;
import com.mmdev.batmanproject.persistence.MovieData;
import com.mmdev.batmanproject.repository.MainRepository;
import com.mmdev.batmanproject.util.Resource;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class MainViewModel extends ViewModel {

    private MainRepository mainRepository;
    private LiveData<List<MovieData>> batmanDataLiveData;
    private LiveData<Resource<Movie>> resourceLiveData;

    @Inject
    public MainViewModel(MainRepository mainRepository) {

        this.mainRepository = mainRepository;
        batmanDataLiveData = mainRepository.getAllDatabaseMovies();
        resourceLiveData = mainRepository.getAllBatmanMovies();
    }

    public LiveData<List<MovieData>> getBatmanLiveData() {
        return batmanDataLiveData;
    }

    public LiveData<Resource<Movie>> getResourceLiveData() {
        return resourceLiveData;
    }
}
