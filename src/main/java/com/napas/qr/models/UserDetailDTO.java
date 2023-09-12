package com.napas.qr.models;

import com.napas.qr.define.UserGroupType;
import com.napas.qr.define.UserStatusType;
import lombok.Data;

import javax.persistence.ManyToOne;


public class UserDetailDTO {

    private  Long id;

    private String username;

    private String fullName;

    private String telephone;
    private String email;

    private UserGroupType userGroupType;

    private String deptName;

    private String merchantName;

    private String userPosition;

    private String userUnit;

    private String userAddress;

    private Long provinceId;

    private Long districtId;

    private UserStatusType status;

    private Long createByUserId;


    public UserDetailDTO(Long id, String username, String fullName, String telephone, String email, UserGroupType userGroupType, String deptName, String merchantName, String userPosition, String userUnit, String userAddress, Long provinceId, Long districtId,UserStatusType status, Long createByUserId) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.telephone = telephone;
        this.email = email;
        this.userGroupType = userGroupType;
        this.deptName = deptName;
        this.merchantName = merchantName;
        this.userPosition = userPosition;
        this.userUnit = userUnit;
        this.userAddress = userAddress;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.status = status;
        this.createByUserId = createByUserId;
    }

    public Long getCreateByUserId() {
        return createByUserId;
    }

    public void setCreateByUserId(Long createByUserId) {
        this.createByUserId = createByUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserGroupType getUserGroupType() {
        return userGroupType;
    }

    public void setUserGroupType(UserGroupType userGroupType) {
        this.userGroupType = userGroupType;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(String userUnit) {
        this.userUnit = userUnit;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public UserStatusType getStatus() {
        return status;
    }

    public void setStatus(UserStatusType status) {
        this.status = status;
    }
}
