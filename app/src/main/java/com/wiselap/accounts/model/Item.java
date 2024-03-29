package com.wiselap.accounts.model;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("field")
    private String item_name;
    @SerializedName("id")
    private int id;

    public Item(String item_name, int id) {
        this.item_name = item_name;
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
