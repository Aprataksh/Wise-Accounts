package com.wiselap.accounts.SignIn;

import com.google.gson.annotations.SerializedName;

class ApplicationUserID {
    @SerializedName("applicationUserId")
    private long applicationUserId;

    public ApplicationUserID(long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public long getApplicationUserId() {
        return applicationUserId;
    }
}
