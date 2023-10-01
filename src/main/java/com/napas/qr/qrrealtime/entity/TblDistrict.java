package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
@Table(name = "TBL_DISTRICT")
@NamedQueries({
    @NamedQuery(name = "TblDistrict.findAll", query = "SELECT t FROM TblDistrict t")})
public class TblDistrict implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DISTRICT")
    @SequenceGenerator(sequenceName = "SEQ_DISTRICT", allocationSize = 1, name = "SEQ_DISTRICT")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DISTRICT_NAME")
    private String districtName;
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "DATE_MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblDistrict")
    private Collection<TblMerchantCorporate> tblMerchantCorporateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblDistrict")
    private Collection<TblMerchantPersonal> tblMerchantPersonalCollection;
    @JoinColumn(name = "PROV_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblProvince tblProvince;

    public TblDistrict() {
    }

    public TblDistrict(Long id) {
        this.id = id;
    }

    public TblDistrict(Long id, String districtName) {
        this.id = id;
        this.districtName = districtName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public TblProvince getTblProvince() {
        return tblProvince;
    }

    public void setTblProvince(TblProvince tblProvince) {
        this.tblProvince = tblProvince;
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
        if (!(object instanceof TblDistrict)) {
            return false;
        }
        TblDistrict other = (TblDistrict) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
