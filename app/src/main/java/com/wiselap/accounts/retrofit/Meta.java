package com.wiselap.accounts.retrofit;

public class Meta {
    private int id;
    private String message;

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "Meta{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
