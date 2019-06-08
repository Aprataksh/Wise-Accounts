package com.wiselap.accounts.interfaces;

public interface ServiceProvider<T> {
     T getService();
     T getWrappedService();
}
