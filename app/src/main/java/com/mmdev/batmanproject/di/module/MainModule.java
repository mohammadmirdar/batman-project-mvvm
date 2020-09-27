package com.mmdev.batmanproject.di.module;

import androidx.recyclerview.widget.RecyclerView;

import com.mmdev.batmanproject.adapter.MovieListAdapter;
import com.mmdev.batmanproject.persistence.BatmanDao;
import com.mmdev.batmanproject.repository.MainRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainModule {

    @Singleton
    @Binds
    abstract MainRepository bindMainRepository(MainRepository mainRepository);

    @Provides
    static RecyclerView.Adapter provideMovieAdapter(MovieListAdapter adapter){
        return new MovieListAdapter();
    }


}
