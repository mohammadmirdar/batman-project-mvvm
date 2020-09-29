package com.mmdev.batmanproject.di.module;

import com.mmdev.batmanproject.view.MainActivity;
import com.mmdev.batmanproject.view.MovieDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {MainViewModelModule.class,
            MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(
            modules = {DetailViewModelModule.class,
                    DetailModule.class}
    )
    abstract MovieDetailActivity contributeMovieDetailActivity();


}
