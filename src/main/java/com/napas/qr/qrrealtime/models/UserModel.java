package com.napas.qr.qrrealtime.models;

import com.napas.qr.qrrealtime.define.ERole;
import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.entity.*;

public class UserModel {
    private String password;
    private Long id;
    private String username;
    private ETargetType targetType;
    private long targetId;
    private TblMasterMerchant masterMerchant;
    private TblMerchantCorporate merchant;
    private TblMerchantBranch branch;
    private TblMerchantCashier cashier;
    private TblMerchantPersonal merchantPersonal;
    private ERole roles;

    public UserModel(String password, Long id, String username, ETargetType targetType, long targetId, TblMasterMerchant masterMerchant, TblMerchantCorporate merchant, TblMerchantBranch branch, TblMerchantCashier cashier, TblMerchantPersonal merchantPersonal, ERole roles) {
        this.password = password;
        this.id = id;
        this.username = username;
        this.targetType = targetType;
        this.targetId = targetId;
        this.masterMerchant = masterMerchant;
        this.merchant = merchant;
        this.branch = branch;
        this.cashier = cashier;
        this.merchantPersonal = merchantPersonal;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public TblMasterMerchant getMasterMerchant() {
        return masterMerchant;
    }

    public void setMasterMerchant(TblMasterMerchant masterMerchant) {
        this.masterMerchant = masterMerchant;
    }

    public TblMerchantCorporate getMerchant() {
        return merchant;
    }

    public void setMerchant(TblMerchantCorporate merchant) {
        this.merchant = merchant;
    }

    public TblMerchantBranch getBranch() {
        return branch;
    }

    public void setBranch(TblMerchantBranch branch) {
        this.branch = branch;
    }

    public TblMerchantCashier getCashier() {
        return cashier;
    }

    public void setCashier(TblMerchantCashier cashier) {
        this.cashier = cashier;
    }

    public TblMerchantPersonal getMerchantPersonal() {
        return merchantPersonal;
    }

    public void setMerchantPersonal(TblMerchantPersonal merchantPersonal) {
        this.merchantPersonal = merchantPersonal;
    }

    public ERole getRoles() {
        return roles;
    }

    public void setRoles(ERole roles) {
        this.roles = roles;
    }


}

