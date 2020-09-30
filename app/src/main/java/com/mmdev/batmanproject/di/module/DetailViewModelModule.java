package com.mmdev.batmanproject.di.module;

import androidx.lifecycle.ViewModel;

import com.mmdev.batmanproject.di.ViewModelKey;
import com.mmdev.batmanproject.viewmodel.DetailViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class DetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindViewModel(DetailViewModel detailViewModel);


}
