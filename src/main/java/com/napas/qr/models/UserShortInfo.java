package com.napas.qr.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.napas.qr.define.UserGroupType;
import com.napas.qr.define.UserStatusType;
import lombok.Data;
import java.util.Date;

@Data
public class UserShortInfo {

    private Long userId;

    private String username;

    private String fullname;

    private UserGroupType userGroupType;

    private String deptName;

    private String merchantName;

    private UserStatusType status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date lastDetailUpdateAt;

    private String modifiedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date dateApproved;

    private String approvedBy;

    public UserShortInfo(Long userId, String username, String fullname, UserGroupType userGroupType, String deptName, String merchantName, UserStatusType status, Date createdAt, String createdBy, Date lastDetailUpdateAt, String modifiedBy, Date dateApproved, String approvedBy) {
        this.userId = userId;
        this.username = username;
        this.fullname = fullname;
        this.userGroupType = userGroupType;
        this.deptName = deptName;
        this.merchantName = merchantName;
        this.status = status;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.lastDetailUpdateAt = lastDetailUpdateAt;
        this.modifiedBy = modifiedBy;
        this.dateApproved = dateApproved;
        this.approvedBy = approvedBy;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public UserStatusType getStatus() {
        return status;
    }

    public void setStatus(UserStatusType status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastDetailUpdateAt() {
        return lastDetailUpdateAt;
    }

    public void setLastDetailUpdateAt(Date lastDetailUpdateAt) {
        this.lastDetailUpdateAt = lastDetailUpdateAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
