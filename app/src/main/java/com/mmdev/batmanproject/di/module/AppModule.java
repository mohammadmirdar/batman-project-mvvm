package com.mmdev.batmanproject.di.module;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mmdev.batmanproject.remote.BatmanApi;
import com.mmdev.batmanproject.util.Constants;

import javax.inject.Singleton;

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
    static BatmanApi provideBatmanApi(Retrofit retrofit){
        return retrofit.create(BatmanApi.class);
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


}
