/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.napas.qr.qrrealtime.entity;

import com.napas.qr.qrrealtime.define.ECreateBankAccountRequestStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author huynx
 */
@Entity
@Table(name = "HIS_CREATE_BANK_ACCOUNT_REQUEST")
@NamedQueries({
    @NamedQuery(name = "HisCreateBankAccountRequest.findAll", query = "SELECT h FROM HisCreateBankAccountRequest h")})
public class HisCreateBankAccountRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ID")
    private String id;
    @Size(max = 20)
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ECreateBankAccountRequestStatus status;
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
    @Column(name = "CREATED_BY")
    private long createdBy;
    
    @Basic(optional = false)
    @Column(name = "SETTLE_BANK_ID")
    private Long settleBankId;

    public HisCreateBankAccountRequest() {
    }

    public HisCreateBankAccountRequest(String id) {
        this.id = id;
    }

    public HisCreateBankAccountRequest(String id, ECreateBankAccountRequestStatus status, Date dateCreated, long createdBy) {
        this.id = id;
        this.status = status;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public ECreateBankAccountRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ECreateBankAccountRequestStatus status) {
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

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getSettleBankId() {
        return settleBankId;
    }

    public void setSettleBankId(Long settleBankId) {
        this.settleBankId = settleBankId;
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
        if (!(object instanceof HisCreateBankAccountRequest)) {
            return false;
        }
        HisCreateBankAccountRequest other = (HisCreateBankAccountRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.napas.qr.qrrealtime.entity.HisCreateBankAccountRequest[ id=" + id + " ]";
    }
    
}
