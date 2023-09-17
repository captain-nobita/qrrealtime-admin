package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author phucdv
 */
@Entity
@Table(name = "TBL_MERCHANT_BRANCH")
@NamedQueries({
    @NamedQuery(name = "TblMerchantBranch.findAll", query = "SELECT t FROM TblMerchantBranch t")})
public class TblMerchantBranch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_MERCHANT_BRANCH")
    @SequenceGenerator(sequenceName = "SEQ_TBL_MERCHANT_BRANCH", allocationSize = 1, name = "SEQ_TBL_MERCHANT_BRANCH")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "BRANCH_CODE")
    private String branchCode;

    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private MerchantStatus status;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CREATED")
    private LocalDateTime dateCreated;
    @Column(name = "DATE_MODIFIED")
    private LocalDateTime dateModified;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY_USER")
    private long createdByUser;
    @Column(name = "MODIFIED_BY_USER")
    private Long modifiedByUser;
    @Column(name = "SETTLE_BANK_ID")
    private Integer settleBankId;
    @Size(max = 20)
    @Column(name = "CREDITOR_ACCOUNT")
    private String creditorAccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "BRANCH_NAME")
    private String branchName;


    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_ACCEPTANCE_STATUS")
    private PaymentAcceptStatus paymentAcceptanceStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMerchantBranch")
    private Collection<TblMerchantCashier> tblMerchantCashierCollection;
    @JoinColumn(name = "MERCHANT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblMerchantCorporate tblMerchantCorporate;

    public TblMerchantBranch() {
    }

    public TblMerchantBranch(Long id) {
        this.id = id;
    }

    public TblMerchantBranch(Long id, String branchCode, MerchantStatus status, LocalDateTime dateCreated, long createdByUser, String branchName) {
        this.id = id;
        this.branchCode = branchCode;
        this.status = status;
        this.dateCreated = dateCreated;
        this.createdByUser = createdByUser;
        this.branchName = branchName;
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
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

    public Integer getSettleBankId() {
        return settleBankId;
    }

    public void setSettleBankId(Integer settleBankId) {
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

    public Collection<TblMerchantCashier> getTblMerchantCashierCollection() {
        return tblMerchantCashierCollection;
    }

    public void setTblMerchantCashierCollection(Collection<TblMerchantCashier> tblMerchantCashierCollection) {
        this.tblMerchantCashierCollection = tblMerchantCashierCollection;
    }

    public TblMerchantCorporate getTblMerchantCorporate() {
        return tblMerchantCorporate;
    }

    public void setTblMerchantCorporate(TblMerchantCorporate tblMerchantCorporate) {
        this.tblMerchantCorporate = tblMerchantCorporate;
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
        if (!(object instanceof TblMerchantBranch)) {
            return false;
        }
        TblMerchantBranch other = (TblMerchantBranch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
