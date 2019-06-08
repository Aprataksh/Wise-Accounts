package com.wiselap.accounts.users;

public class UserReturnModel {
    long accountingUserId;
    long applicationUserId;
    String userName;
    String userId;
    String userProfile;

    public long getAccountingUserId() {
        return accountingUserId;
    }

    public long getApplicationUserId() {
        return applicationUserId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserProfile() {
        return userProfile;
    }
}
