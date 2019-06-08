package com.wiselap.accounts.model;

public class OfficeEntity {
    private long applicationUserId;
    private String officeName;
    private String contactNo;
    private String ownerName;
    private String address;
    private String profileEntity = "office";


    public OfficeEntity(String officeName, String contactNo, String ownerName, String address) {
        this.officeName = officeName;
        this.contactNo = contactNo;
        this.ownerName = ownerName;
        this.address = address;
    }

    public long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileEntity() {
        return profileEntity;
    }

    public void setProfileEntity(String profileEntity) {
        this.profileEntity = profileEntity;
    }
}

