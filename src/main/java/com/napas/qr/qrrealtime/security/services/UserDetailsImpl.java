package com.napas.qr.qrrealtime.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.entity.*;
import com.napas.qr.qrrealtime.models.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private ETargetType targetType;
    private long targetId;
    private TblMasterMerchant masterMerchant;
    private TblMerchantCorporate merchant;
    private TblMerchantBranch branch;
    private TblMerchantCashier cashier;
    private TblMerchantPersonal merchantPersonal;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password,
                           Collection<? extends GrantedAuthority> authorities, ETargetType targetType,
                           long targetId,
                           TblMasterMerchant masterMerchant,
                           TblMerchantCorporate merchant,
                           TblMerchantBranch branch,
                           TblMerchantCashier cashier,
                           TblMerchantPersonal merchantPersonal
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.targetType = targetType;
        this.targetId = targetId;
        this.masterMerchant = masterMerchant;
        this.merchant = merchant;
        this.branch = branch;
        this.cashier = cashier;
        this.merchantPersonal = merchantPersonal;
    }

    public static UserDetailsImpl build(UserModel user) {
        SimpleGrantedAuthority simpleRole = new SimpleGrantedAuthority(user.getRoles().toString());
        List<GrantedAuthority> listRoles = new ArrayList<>();
        listRoles.add(simpleRole);

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                listRoles,
                user.getTargetType(),
                user.getTargetId(),
                user.getMasterMerchant(),
                user.getMerchant(),
                user.getBranch(),
                user.getCashier(),
                user.getMerchantPersonal()
        );
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




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}