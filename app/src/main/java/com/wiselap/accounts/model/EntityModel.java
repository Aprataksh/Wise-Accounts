package com.wiselap.accounts.model;

public class EntityModel {

        private long accountingProfileId;
        private String officeName;
        private String name;
        private String contactNo;
        private String ownerName;
        private String address;
        private String profileEntity;
        private String applicationUserId;

    public EntityModel(long accountingProfileId, String officeName, String name, String contactNo, String ownerName, String address, String profileEntity, String applicationUserId) {
        this.accountingProfileId = accountingProfileId;
        this.officeName = officeName;
        this.name = name;
        this.contactNo = contactNo;
        this.ownerName = ownerName;
        this.address = address;
        this.profileEntity = profileEntity;
        this.applicationUserId = applicationUserId;
    }

    public long getAccountingProfileId() {
        return accountingProfileId;
    }

    public void setAccountingProfileId(long accountingProfileId) {
        this.accountingProfileId = accountingProfileId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
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

    public String getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(String applicationUserId) {
        this.applicationUserId = applicationUserId;
    }
}
