package com.mmdev.batmanproject.di.module;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mmdev.batmanproject.persistence.MovieDao;
import com.mmdev.batmanproject.persistence.MovieDatabase;
import com.mmdev.batmanproject.remote.MovieApi;
import com.mmdev.batmanproject.util.Constants;
import com.mmdev.batmanproject.viewmodel.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static MovieApi provideBatmanApi(Retrofit retrofit){
        return retrofit.create(MovieApi.class);
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOption(){
        return RequestOptions.fitCenterTransform().centerCrop();
    }

    @Singleton
    @Provides
    static RequestManager provideRequestManager(Application application , RequestOptions requestOptions){
        return Glide
                .with(application.getApplicationContext())
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static MovieDatabase provideRoomDatabase(Application application){

        return Room.databaseBuilder(application.getApplicationContext(),
                MovieDatabase.class,
                "weather_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    static MovieDao provideMovieDao(MovieDatabase database){
        return database.batmanDao();
    }

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
