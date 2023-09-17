package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author phucdv
 */
@Entity
@Table(name = "HIS_USER_ACTION")
@NamedQueries({
    @NamedQuery(name = "HisUserAction.findAll", query = "SELECT h FROM HisUserAction h")})
public class HisUserAction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ACTION_CODE")
    private String actionCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTION_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionDatetime;
    @Size(max = 2000)
    @Column(name = "ACTION_DETAIL")
    private String actionDetail;

    public HisUserAction() {
    }

    public HisUserAction(Long id) {
        this.id = id;
    }

    public HisUserAction(Long id, long userId, String actionCode, Date actionDatetime) {
        this.id = id;
        this.userId = userId;
        this.actionCode = actionCode;
        this.actionDatetime = actionDatetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public Date getActionDatetime() {
        return actionDatetime;
    }

    public void setActionDatetime(Date actionDatetime) {
        this.actionDatetime = actionDatetime;
    }

    public String getActionDetail() {
        return actionDetail;
    }

    public void setActionDetail(String actionDetail) {
        this.actionDetail = actionDetail;
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
        if (!(object instanceof HisUserAction)) {
            return false;
        }
        HisUserAction other = (HisUserAction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.napas.achoffline.reportoffline.entity.HisUserAction[ id=" + id + " ]";
    }
    
}
