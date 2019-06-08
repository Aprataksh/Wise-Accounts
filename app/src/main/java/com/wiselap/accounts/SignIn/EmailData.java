package com.wiselap.accounts.SignIn;

public class EmailData {

    private long applicationUserId;
    private long loginId;
    private long applicationTypeId;
    private String autherisedKey;
    private long billingSoftUsed;

    public long getApplicationUserId() {
        return applicationUserId;
    }

    public long getLoginId() {
        return loginId;
    }

    public long getApplicationTypeId() {
        return applicationTypeId;
    }

    public String getAutherisedKey() {
        return autherisedKey;
    }

    public long getBillingSoftUsed() {
        return billingSoftUsed;
    }


}