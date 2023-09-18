package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.napas.qr.qrrealtime.define.EBranchAccountSettledType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author phucdv
 */
@Entity
@Table(name = "TBL_MERCHANT_CORPORATE")
@NamedQueries({
    @NamedQuery(name = "TblMerchantCorporate.findAll", query = "SELECT t FROM TblMerchantCorporate t")})
public class TblMerchantCorporate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_MERCHANT_CORPORATE")
    @SequenceGenerator(sequenceName = "SEQ_TBL_MERCHANT_CORPORATE", allocationSize = 1, name = "SEQ_TBL_MERCHANT_CORPORATE")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "MERCHANT_CODE")
    private String merchantCode;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "STATUS")
    private MerchantStatus status;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NAME")
    private String name;
    @Size(max = 20)
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Size(max = 1000)
    @Column(name = "ADDRESS_LINE")
    private String addressLine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "DATE_MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY_USER")
    private long createdByUser;
    @Column(name = "MODIFIED_BY_USER")
    private Long modifiedByUser;

    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "BRANCH_ACCOUNT_SETTLED_TYPE")
    private EBranchAccountSettledType branchAccountSettledType;

    @Size(max = 30)
    @Column(name = "CREDITOR_ACCOUNT")
    private String creditorAccount;


    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_ACCEPTANCE_STATUS")
    private PaymentAcceptStatus paymentAcceptanceStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMerchantCorporate")
    private Collection<TblMerchantBranch> tblMerchantBranchCollection;

    @NotNull
    @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private TblDistrict tblDistrict;

    @JoinColumn(name = "MM_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblMasterMerchant tblMasterMerchant;

    @JoinColumn(name = "SETTLE_BANK_ID", referencedColumnName = "ID")
    @ManyToOne
    private TblSettleBank tblSettleBank;

    public TblMerchantCorporate() {
    }

    public TblMerchantCorporate(Long id) {
        this.id = id;
    }

    public TblMerchantCorporate(Long id, String merchantCode, MerchantStatus status, String name, Date dateCreated, long createdByUser, EBranchAccountSettledType branchAccountSettledType) {
        this.id = id;
        this.merchantCode = merchantCode;
        this.status = status;
        this.name = name;
        this.dateCreated = dateCreated;
        this.createdByUser = createdByUser;
        this.branchAccountSettledType = branchAccountSettledType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public MerchantStatus getStatus() {
        return status;
    }

    public void setStatus(MerchantStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
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

    public EBranchAccountSettledType getBranchAccountSettledType() {
        return branchAccountSettledType;
    }

    public void setBranchAccountSettledType(EBranchAccountSettledType branchAccountSettledType) {
        this.branchAccountSettledType = branchAccountSettledType;
    }

    public String getCreditorAccount() {
        return creditorAccount;
    }

    public void setCreditorAccount(String creditorAccount) {
        this.creditorAccount = creditorAccount;
    }

    public PaymentAcceptStatus getPaymentAcceptanceStatus() {
        return paymentAcceptanceStatus;
    }

    public void setPaymentAcceptanceStatus(PaymentAcceptStatus paymentAcceptanceStatus) {
        this.paymentAcceptanceStatus = paymentAcceptanceStatus;
    }

    public Collection<TblMerchantBranch> getTblMerchantBranchCollection() {
        return tblMerchantBranchCollection;
    }

    public void setTblMerchantBranchCollection(Collection<TblMerchantBranch> tblMerchantBranchCollection) {
        this.tblMerchantBranchCollection = tblMerchantBranchCollection;
    }

    public TblDistrict getTblDistrict() {
        return tblDistrict;
    }

    public void setTblDistrict(TblDistrict tblDistrict) {
        this.tblDistrict = tblDistrict;
    }

    public TblMasterMerchant getTblMasterMerchant() {
        return tblMasterMerchant;
    }

    public void setTblMasterMerchant(TblMasterMerchant tblMasterMerchant) {
        this.tblMasterMerchant = tblMasterMerchant;
    }

    public TblSettleBank getTblSettleBank() {
        return tblSettleBank;
    }

    public void setTblSettleBank(TblSettleBank tblSettleBank) {
        this.tblSettleBank = tblSettleBank;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblMerchantCorporate)) {
            return false;
        }
        TblMerchantCorporate other = (TblMerchantCorporate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
