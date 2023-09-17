package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author phucdv
 */
@Entity
@Table(name = "TBL_MERCHANT_PERSONAL")
@NamedQueries({
    @NamedQuery(name = "TblMerchantPersonal.findAll", query = "SELECT t FROM TblMerchantPersonal t")})
public class TblMerchantPersonal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_MERCHANT_PERSONAL")
    @SequenceGenerator(sequenceName = "SEQ_TBL_MERCHANT_PERSONAL", allocationSize = 1, name = "SEQ_TBL_MERCHANT_PERSONAL")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "MERCHANT_CODE")
    private String merchantCode;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
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
    @Size(min = 1, max = 30)
    @Column(name = "CREDITOR_ACCOUNT")
    private String creditorAccount;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_ACCEPTANCE_STATUS")
    private PaymentAcceptStatus paymentAcceptanceStatus;

    @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblDistrict tblDistrict;
    @JoinColumn(name = "MM_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblMasterMerchant tblMasterMerchant;
    @JoinColumn(name = "SETTLE_BANK_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblSettleBank tblSettleBank;

    @Column(name = "OWNER_NAME")
    private String ownerName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    public TblMerchantPersonal() {
    }

    public TblMerchantPersonal(Long id, String merchantCode, MerchantStatus status, String name, String phoneNumber, String addressLine, Date dateCreated, Date dateModified, long createdByUser, Long modifiedByUser, String creditorAccount, PaymentAcceptStatus paymentAcceptanceStatus, TblDistrict tblDistrict, TblMasterMerchant tblMasterMerchant, TblSettleBank tblSettleBank, String ownerName, String emailAddress) {
        this.id = id;
        this.merchantCode = merchantCode;
        this.status = status;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressLine = addressLine;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.createdByUser = createdByUser;
        this.modifiedByUser = modifiedByUser;
        this.creditorAccount = creditorAccount;
        this.paymentAcceptanceStatus = paymentAcceptanceStatus;
        this.tblDistrict = tblDistrict;
        this.tblMasterMerchant = tblMasterMerchant;
        this.tblSettleBank = tblSettleBank;
        this.ownerName = ownerName;
        this.emailAddress = emailAddress;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
