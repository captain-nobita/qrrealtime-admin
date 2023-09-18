package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "TBL_SETTLE_BANK")
@NamedQueries({
    @NamedQuery(name = "TblSettleBank.findAll", query = "SELECT t FROM TblSettleBank t")})
public class TblSettleBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "BANK_ID")
    private String bankId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "BANK_RECEIVE_CODE")
    private String bankReceiveCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUS")
    private String status;
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
    @OneToMany(mappedBy = "tblSettleBank")
    @JsonIgnore
    private Collection<TblMerchantCorporate> tblMerchantCorporateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblSettleBank")
    @JsonIgnore
    private Collection<TblMerchantPersonal> tblMerchantPersonalCollection;

    public TblSettleBank() {
    }

    public TblSettleBank(Long id) {
        this.id = id;
    }

    public TblSettleBank(Long id, String bankId, String bankReceiveCode, String status, Date dateCreated, long createdByUser) {
        this.id = id;
        this.bankId = bankId;
        this.bankReceiveCode = bankReceiveCode;
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

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankReceiveCode() {
        return bankReceiveCode;
    }

    public void setBankReceiveCode(String bankReceiveCode) {
        this.bankReceiveCode = bankReceiveCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Collection<TblMerchantCorporate> getTblMerchantCorporateCollection() {
        return tblMerchantCorporateCollection;
    }

    public void setTblMerchantCorporateCollection(Collection<TblMerchantCorporate> tblMerchantCorporateCollection) {
        this.tblMerchantCorporateCollection = tblMerchantCorporateCollection;
    }

    public Collection<TblMerchantPersonal> getTblMerchantPersonalCollection() {
        return tblMerchantPersonalCollection;
    }

    public void setTblMerchantPersonalCollection(Collection<TblMerchantPersonal> tblMerchantPersonalCollection) {
        this.tblMerchantPersonalCollection = tblMerchantPersonalCollection;
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
        if (!(object instanceof TblSettleBank)) {
            return false;
        }
        TblSettleBank other = (TblSettleBank) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.napas.achoffline.reportoffline.entity.TblSettleBank[ id=" + id + " ]";
    }
    
}
