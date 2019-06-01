package com.wiselap.accounts.model;

public class Configuration {
    private int images;
    private String type;

    public Configuration(int images, String type) {
        this.images = images;
        this.type = type;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
