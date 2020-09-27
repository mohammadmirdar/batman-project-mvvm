package com.mmdev.batmanproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mmdev.batmanproject.model.Batman;
import com.mmdev.batmanproject.persistence.BatmanData;
import com.mmdev.batmanproject.repository.MainRepository;
import com.mmdev.batmanproject.util.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainViewModel extends ViewModel {

    private MainRepository mainRepository;
    private LiveData<List<BatmanData>> batmanDataLiveData;
    private LiveData<Resource<Batman>> resourceLiveData;

    @Inject
    public MainViewModel(MainRepository mainRepository) {

        this.mainRepository = mainRepository;
        batmanDataLiveData = mainRepository.getAllDatabaseMovies();
        resourceLiveData = mainRepository.getAllBatmanMovies();
    }

    public LiveData<List<BatmanData>> getBatmanLiveData() {
        return batmanDataLiveData;
    }

    public LiveData<Resource<Batman>> getResourceLiveData() {
        return resourceLiveData;
    }
}
