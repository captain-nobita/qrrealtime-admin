package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author phucdv
 */
@Entity
@Table(name = "TBL_NAPAS_BANK")
@NamedQueries({
    @NamedQuery(name = "TblNapasBank.findAll", query = "SELECT t FROM TblNapasBank t")})
public class TblNapasBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "BIC")
    private String bic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "BANK_ID")
    private String bankId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "BEN_ID")
    private String benId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "BANK_SHORT_NAME")
    private String bankShortName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "BANK_FULL_NAME_VI")
    private String bankFullNameVi;
    @Size(max = 200)
    @Column(name = "BANK_FULL_NAME_EN")
    private String bankFullNameEn;

    public TblNapasBank() {
    }

    public TblNapasBank(Integer id) {
        this.id = id;
    }

    public TblNapasBank(Integer id, String bankId, String benId, String bankShortName, String bankFullNameVi) {
        this.id = id;
        this.bankId = bankId;
        this.benId = benId;
        this.bankShortName = bankShortName;
        this.bankFullNameVi = bankFullNameVi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBenId() {
        return benId;
    }

    public void setBenId(String benId) {
        this.benId = benId;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public String getBankFullNameVi() {
        return bankFullNameVi;
    }

    public void setBankFullNameVi(String bankFullNameVi) {
        this.bankFullNameVi = bankFullNameVi;
    }

    public String getBankFullNameEn() {
        return bankFullNameEn;
    }

    public void setBankFullNameEn(String bankFullNameEn) {
        this.bankFullNameEn = bankFullNameEn;
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
        if (!(object instanceof TblNapasBank)) {
            return false;
        }
        TblNapasBank other = (TblNapasBank) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.napas.achoffline.reportoffline.entity.TblNapasBank[ id=" + id + " ]";
    }
    
}
