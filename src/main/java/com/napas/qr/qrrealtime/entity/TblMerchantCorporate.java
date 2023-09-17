package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUS")
    private String status;
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
    @Size(min = 1, max = 20)
    @Column(name = "BRANCH_ACCOUNT_SETTLED_TYPE")
    private String branchAccountSettledType;
    @Size(max = 30)
    @Column(name = "CREDITOR_ACCOUNT")
    private String creditorAccount;
    @Size(max = 20)
    @Column(name = "PAYMENT_ACCEPTANCE_STATUS")
    private String paymentAcceptanceStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMerchantCorporate")
    private Collection<TblMerchantBranch> tblMerchantBranchCollection;
    @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
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

    public TblMerchantCorporate(Long id, String merchantCode, String status, String name, Date dateCreated, long createdByUser, String branchAccountSettledType) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getBranchAccountSettledType() {
        return branchAccountSettledType;
    }

    public void setBranchAccountSettledType(String branchAccountSettledType) {
        this.branchAccountSettledType = branchAccountSettledType;
    }

    public String getCreditorAccount() {
        return creditorAccount;
    }

    public void setCreditorAccount(String creditorAccount) {
        this.creditorAccount = creditorAccount;
    }

    public String getPaymentAcceptanceStatus() {
        return paymentAcceptanceStatus;
    }

    public void setPaymentAcceptanceStatus(String paymentAcceptanceStatus) {
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

    @Override
    public String toString() {
        return "com.napas.achoffline.reportoffline.entity.TblMerchantCorporate[ id=" + id + " ]";
    }
    
}
