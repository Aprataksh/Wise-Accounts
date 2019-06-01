package com.wiselap.accounts.retrofit;

import com.wiselap.accounts.URLS;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInstance {
    private static Retrofit customRetrofitInstance;
    private static Retrofit retrofitInstance;

    public static Retrofit getCustomRetrofitInstance(){
        if (customRetrofitInstance == null){
            customRetrofitInstance = new Retrofit.Builder().
                    baseUrl(URLS.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(new UnwrapConverterFactory(GsonConverterFactory.create())).build();
        }
        return customRetrofitInstance;
    }

    public static Retrofit getRetrofitInstance(){
        if (retrofitInstance == null){

            retrofitInstance = new Retrofit.Builder().
                    baseUrl(URLS.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofitInstance;
    }

}


