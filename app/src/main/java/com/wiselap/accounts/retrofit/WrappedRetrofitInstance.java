package com.wiselap.accounts.retrofit;

public class WrappedRetrofitInstance<T> {
    private RetrofitInstance retrofitInstance,customRetrofitInstance;

    public WrappedRetrofitInstance(RetrofitInstance retrofitInstance, RetrofitInstance customRetrofitInstance) {
        this.retrofitInstance = retrofitInstance;
        this.customRetrofitInstance = customRetrofitInstance;
    }

    public RetrofitInstance getRetrofitInstance() {
        return retrofitInstance;
    }

    public RetrofitInstance getCustomRetrofitInstance() {
        return customRetrofitInstance;
    }
}
