package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.napas.qr.qrrealtime.define.MerchantStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author phucdv
 */
@Entity
@Table(name = "TBL_MERCHANT_CASHIER")
@NamedQueries({
    @NamedQuery(name = "TblMerchantCashier.findAll", query = "SELECT t FROM TblMerchantCashier t")})
public class TblMerchantCashier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_MERCHANT_CASHIER")
    @SequenceGenerator(sequenceName = "SEQ_TBL_MERCHANT_CASHIER", allocationSize = 1, name = "SEQ_TBL_MERCHANT_CASHIER")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CASHIER_CODE")
    private String cashierCode;
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
    private Long createdByUser;
    @Column(name = "MODIFIED_BY_USER")
    private Long modifiedByUser;
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private TblMerchantBranch tblMerchantBranch;

    public TblMerchantCashier() {
    }

    public TblMerchantCashier(Long id) {
        this.id = id;
    }

    public TblMerchantCashier(Long id, String cashierCode, MerchantStatus status, LocalDateTime dateCreated, long createdByUser) {
        this.id = id;
        this.cashierCode = cashierCode;
        this.status = status;
        this.dateCreated = dateCreated;
        this.createdByUser = createdByUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCashierCode() {
        return cashierCode;
    }

    public void setCashierCode(String cashierCode) {
        this.cashierCode = cashierCode;
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

    public TblMerchantBranch getTblMerchantBranch() {
        return tblMerchantBranch;
    }

    public void setTblMerchantBranch(TblMerchantBranch tblMerchantBranch) {
        this.tblMerchantBranch = tblMerchantBranch;
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
        if (!(object instanceof TblMerchantCashier)) {
            return false;
        }
        TblMerchantCashier other = (TblMerchantCashier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
