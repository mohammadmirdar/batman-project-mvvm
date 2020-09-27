package com.mmdev.batmanproject.di.module;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mmdev.batmanproject.di.ViewModelKey;
import com.mmdev.batmanproject.persistence.BatmanDao;
import com.mmdev.batmanproject.repository.MainRepository;
import com.mmdev.batmanproject.viewmodel.MainViewModel;
import com.mmdev.batmanproject.viewmodel.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindViewModel(MainViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Provides
    static MainRepository provideMainRepository(Application application){
        return new MainRepository(application);
    }

}
