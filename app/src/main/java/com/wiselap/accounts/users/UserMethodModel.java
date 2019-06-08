package com.wiselap.accounts.users;

public class UserMethodModel {
    long applicationUserId;

    public UserMethodModel(long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public long getApplicationUserId() {
        return applicationUserId;
    }
}
