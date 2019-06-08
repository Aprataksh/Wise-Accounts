package com.wiselap.accounts.model;

public class PersonalEntity {
    private long applicationUserId;
    private String name;
    private String contactNo;
    private String address;
    private String profileEntity = "personal";

    public PersonalEntity(String name, String contactNo, String address) {
        this.name = name;
        this.contactNo = contactNo;
        this.address = address;
    }

    public long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
