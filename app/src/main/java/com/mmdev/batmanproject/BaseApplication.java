package com.mmdev.batmanproject;

import com.mmdev.batmanproject.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
/**
 * this class provide application level elements
 *
 * @author Mohammad Mirdar
 * @version 1.0
 * @since 2020-09-28
 */
public class BaseApplication extends DaggerApplication {

    /** returns Android Injector that provide Application class all over the app.
     *
     * @return Application to use every where in dagger modules and components.
     */
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
