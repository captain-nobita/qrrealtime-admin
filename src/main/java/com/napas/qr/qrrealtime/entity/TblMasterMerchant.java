package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author phucdv
 */
@Entity
@Table(name = "TBL_MASTER_MERCHANT")
@NamedQueries({
    @NamedQuery(name = "TblMasterMerchant.findAll", query = "SELECT t FROM TblMasterMerchant t")})
public class TblMasterMerchant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "MM_CODE")
    private String mmCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "MM_NAME")
    private String mmName;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "VIEW_MERCHANT_PAYMENT")
    private String viewMerchantPayment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMasterMerchant")
    private Collection<TblMerchantCorporate> tblMerchantCorporateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMasterMerchant")
    private Collection<TblMerchantPersonal> tblMerchantPersonalCollection;

    public TblMasterMerchant() {
    }

    public TblMasterMerchant(Long id) {
        this.id = id;
    }

    public TblMasterMerchant(Long id, String mmCode, String status, String mmName, LocalDateTime dateCreated, long createdByUser, String viewMerchantPayment) {
        this.id = id;
        this.mmCode = mmCode;
        this.status = status;
        this.mmName = mmName;
        this.dateCreated = dateCreated;
        this.createdByUser = createdByUser;
        this.viewMerchantPayment = viewMerchantPayment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMmCode() {
        return mmCode;
    }

    public void setMmCode(String mmCode) {
        this.mmCode = mmCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMmName() {
        return mmName;
    }

    public void setMmName(String mmName) {
        this.mmName = mmName;
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

    public String getViewMerchantPayment() {
        return viewMerchantPayment;
    }

    public void setViewMerchantPayment(String viewMerchantPayment) {
        this.viewMerchantPayment = viewMerchantPayment;
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
        if (!(object instanceof TblMasterMerchant)) {
            return false;
        }
        TblMasterMerchant other = (TblMasterMerchant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
