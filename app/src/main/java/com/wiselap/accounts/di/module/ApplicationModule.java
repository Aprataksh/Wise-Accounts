package com.wiselap.accounts.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.wiselap.accounts.base_class.Schedulers;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.interfaces.SchedulerProvider;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class ApplicationModule {



    @Binds
    abstract Context provideContext(Application application);

    @Provides
    static CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }


    @Provides
    static SchedulerProvider provideScheduler(){
        return new Schedulers();
    }

    @Provides
    static SharedPreferences provideSharedpref(Application application){
        return application.getSharedPreferences(AppConstants.PREFERENCES_FILE,Context.MODE_PRIVATE);
    }



}
