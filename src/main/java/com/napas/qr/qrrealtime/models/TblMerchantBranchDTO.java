package com.napas.qr.qrrealtime.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantCashier;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Data
public class TblMerchantBranchDTO {

    private Long id;

    private String branchCode;

    private MerchantStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date dateCreated;

    private Date dateModified;

    private Long createdByUser;

    private Long modifiedByUser;

    private Long settleBankId;

    private String creditorAccount;

    private String branchName;


    private PaymentAcceptStatus paymentAcceptanceStatus;


    public TblMerchantBranchDTO() {
    }


    public TblMerchantBranchDTO(Long id, String branchCode, MerchantStatus status, Date dateCreated, Date dateModified, Long createdByUser, Long modifiedByUser, Long settleBankId, String creditorAccount, String branchName, PaymentAcceptStatus paymentAcceptanceStatus) {
        this.id = id;
        this.branchCode = branchCode;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.createdByUser = createdByUser;
        this.modifiedByUser = modifiedByUser;
        this.settleBankId = settleBankId;
        this.creditorAccount = creditorAccount;
        this.branchName = branchName;
        this.paymentAcceptanceStatus = paymentAcceptanceStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public MerchantStatus getStatus() {
        return status;
    }

    public void setStatus(MerchantStatus status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public long getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(long createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Long getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(Long modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public Long getSettleBankId() {
        return settleBankId;
    }

    public void setSettleBankId(Long settleBankId) {
        this.settleBankId = settleBankId;
    }

    public String getCreditorAccount() {
        return creditorAccount;
    }

    public void setCreditorAccount(String creditorAccount) {
        this.creditorAccount = creditorAccount;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public PaymentAcceptStatus getPaymentAcceptanceStatus() {
        return paymentAcceptanceStatus;
    }

    public void setPaymentAcceptanceStatus(PaymentAcceptStatus paymentAcceptanceStatus) {
        this.paymentAcceptanceStatus = paymentAcceptanceStatus;
    }
}
