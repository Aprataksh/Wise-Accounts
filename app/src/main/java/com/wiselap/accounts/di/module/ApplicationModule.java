package com.wiselap.accounts.di.module;

import android.app.Application;
import android.content.Context;

import com.wiselap.accounts.base_class.Schedulers;
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



}
