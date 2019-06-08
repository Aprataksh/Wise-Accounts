package com.wiselap.accounts.SignIn;

import java.io.Serializable;

public class Accounts implements Serializable {

    private String name;
    private String profile;
    private String profileEntity;
    private long accountingProfileId;
    private long shopAgentId;

    public String getName() {
        return name;
    }

    public String getProfile() {
        return profile;
    }

    public String getProfileEntity() {
        return profileEntity;
    }

    public long getAccountingProfileId() {
        return accountingProfileId;
    }

    public long getShopAgentId() {
        return shopAgentId;
    }
}