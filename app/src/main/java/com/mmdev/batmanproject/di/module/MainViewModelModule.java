package com.mmdev.batmanproject.di.module;

import androidx.lifecycle.ViewModel;

import com.mmdev.batmanproject.di.ViewModelKey;
import com.mmdev.batmanproject.viewmodel.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindViewModel(MainViewModel viewModel);



}
