package com.mmdev.batmanproject.di.component;

import android.app.Application;

import com.mmdev.batmanproject.BaseApplication;
import com.mmdev.batmanproject.di.module.ActivityBuildersModule;
import com.mmdev.batmanproject.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
@Singleton
@Component(
        modules = {AndroidInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class}
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
