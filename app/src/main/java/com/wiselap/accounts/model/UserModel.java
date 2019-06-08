package com.wiselap.accounts.model;

public class UserModel {

    String username;
    String designation;

    public UserModel(String username, String designation) {
        this.username = username;
        this.designation = designation;
    }

    public String getUsername() {
        return username;
    }

    public String getDesignation() {
        return designation;
    }
}
