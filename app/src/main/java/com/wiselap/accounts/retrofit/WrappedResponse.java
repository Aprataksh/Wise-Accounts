package com.wiselap.accounts.retrofit;

public class WrappedResponse<T> {
    private Meta meta;
    private T data;

    public Meta getMeta() {
        return meta;
    }

    public T getData() {
        return data;
    }


    @Override
    public String toString() {
        return "WrappedResponse{" +
                "meta=" + meta +
                '}';
    }
}
