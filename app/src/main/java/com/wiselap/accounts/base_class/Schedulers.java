package com.wiselap.accounts.base_class;


import com.wiselap.accounts.interfaces.SchedulerProvider;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class Schedulers implements SchedulerProvider {
    public Scheduler getIoScheduler() {
        return io.reactivex.schedulers.Schedulers.io();
    }
    public Scheduler getUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
