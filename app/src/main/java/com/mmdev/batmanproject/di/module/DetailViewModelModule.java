package com.mmdev.batmanproject.di.module;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mmdev.batmanproject.di.ViewModelKey;
import com.mmdev.batmanproject.repository.DetailRepository;
import com.mmdev.batmanproject.viewmodel.DetailViewModel;
import com.mmdev.batmanproject.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class DetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindViewModel(DetailViewModel detailViewModel);


}
