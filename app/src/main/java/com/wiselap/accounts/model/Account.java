package com.wiselap.accounts.model;

public class Account {
    private String account_name;
    private  String account_type;

    public Account(String account_name, String account_type){
        this.account_name=account_name;
        this.account_type=account_type;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}
