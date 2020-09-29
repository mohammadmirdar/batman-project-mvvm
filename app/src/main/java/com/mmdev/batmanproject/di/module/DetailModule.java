package com.mmdev.batmanproject.di.module;

import com.mmdev.batmanproject.repository.DetailRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DetailModule {

    @Singleton
    @Provides
    static DetailRepository provideDetailRepository(){
        return new DetailRepository();
    }
}
