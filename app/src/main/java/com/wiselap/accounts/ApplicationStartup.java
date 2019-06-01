package com.wiselap.accounts;

import com.wiselap.accounts.di.component.AppComponent;
import com.wiselap.accounts.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class ApplicationStartup extends DaggerApplication {
    protected AppComponent appComponent;
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent= DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
