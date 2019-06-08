package com.wiselap.accounts.retrofit;


import android.util.Log;

import com.wiselap.accounts.RequestCode;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class WrappedResponseBodyConverter<T> implements Converter<ResponseBody,T> {

    private Converter<ResponseBody, WrappedResponse<T>> converter;
    private final String TAG = WrappedResponseBodyConverter.class.getSimpleName();

    public WrappedResponseBodyConverter(Converter<ResponseBody, WrappedResponse<T>> converter) {
        this.converter = converter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        WrappedResponse<T> response = converter.convert(value);
        Log.d(TAG, "Checking raw response "+response.getMeta()+" "+response.getData());
        if (response.getMeta().getId() == RequestCode.SUCCESS) {
            return response.getData();
        }
        throw new WrappedError(response.getMeta().getMessage());
    }
}
