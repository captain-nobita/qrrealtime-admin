package com.napas.qr.qrrealtime.payload.response;

import com.napas.qr.qrrealtime.define.ETargetType;

import java.util.List;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private ETargetType targetType;
    private long targetId;
    private String masterMerchantName;
    private String merchantName;
    private String branchName;
    private String cashierCode;

    public JwtResponse(String token, Long id, String username, String email, List<String> roles, ETargetType targetType, long targetId, String masterMerchantName, String merchantName, String branchName, String cashierCode) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.targetType = targetType;
        this.targetId = targetId;
        this.masterMerchantName = masterMerchantName;
        this.merchantName = merchantName;
        this.branchName = branchName;
        this.cashierCode = cashierCode;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String type) {
        this.type = type;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public ETargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(ETargetType targetType) {
        this.targetType = targetType;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public String getMasterMerchantName() {
        return masterMerchantName;
    }

    public void setMasterMerchantName(String masterMerchantName) {
        this.masterMerchantName = masterMerchantName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCashierCode() {
        return cashierCode;
    }

    public void setCashierCode(String cashierCode) {
        this.cashierCode = cashierCode;
    }


}
