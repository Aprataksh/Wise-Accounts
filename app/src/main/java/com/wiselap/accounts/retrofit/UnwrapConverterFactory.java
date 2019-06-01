package com.wiselap.accounts.retrofit;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnwrapConverterFactory extends Converter.Factory {
    private GsonConverterFactory factory;

    public UnwrapConverterFactory(GsonConverterFactory factory) {
          this.factory = factory;
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
        Type wrappedType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{type};
            }

            @Override
            public Type getRawType() {
                return WrappedResponse.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
        Converter<ResponseBody,?>gsonConverter = factory.responseBodyConverter(wrappedType, annotations, retrofit);
        return new WrappedResponseBodyConverter(gsonConverter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return factory
                .requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }
}


