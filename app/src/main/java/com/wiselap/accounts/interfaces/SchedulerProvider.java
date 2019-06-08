package com.wiselap.accounts.interfaces;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    Scheduler getIoScheduler();
    Scheduler getUiScheduler();
}
