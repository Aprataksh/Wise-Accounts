package com.wiselap.accounts.retrofit;

import java.io.IOException;

public class WrappedError extends IOException {
    private  String errorMessage;

    public WrappedError(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public String getRuntimeError() {
        return errorMessage;
    }
}
