package com.wiselap.accounts.di.component;

import android.app.Application;

import com.wiselap.accounts.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;



/*@Component.Builder: We might want to bind some instance to Component.
 In this case we can create an interface with @Component.
 Builder annotation and add whatever method we want to add to builder.

 */
@Singleton
@Component(modules = {ApplicationModule.class,
                      AndroidSupportInjectionModule.class,
                      ActivityBuilder.class,
                      })
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
