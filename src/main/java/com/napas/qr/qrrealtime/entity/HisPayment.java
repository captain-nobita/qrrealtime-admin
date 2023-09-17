package com.napas.qr.qrrealtime.entity;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.napas.qr.qrrealtime.define.EMerchantType;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "HIS_PAYMENT")
@NamedQueries({
    @NamedQuery(name = "HisPayment.findAll", query = "SELECT h FROM HisPayment h")})
public class HisPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "SEQ_NO")
    private Long seqNo;
    @Size(max = 12)
    @Column(name = "MTI")
    private String mti;
    @Size(max = 57)
    @Column(name = "CARD_NO")
    private String cardNo;
    @Size(max = 18)
    @Column(name = "PROC_CODE")
    private String procCode;
    @Size(max = 30)
    @Column(name = "ORIGINAL_DATE")
    private String originalDate;
    @Size(max = 18)
    @Column(name = "TRACE_NO")
    private String traceNo;
    @Size(max = 36)
    @Column(name = "REF_NO")
    private String refNo;
    @Size(max = 33)
    @Column(name = "ACQ_ID")
    private String acqId;
    @Size(max = 33)
    @Column(name = "ISS_ID")
    private String issId;
    @Size(max = 18)
    @Column(name = "APPROVAL_CODE")
    private String approvalCode;
    @Size(max = 9)
    @Column(name = "RESPONSE_CODE")
    private String responseCode;
    @Size(max = 24)
    @Column(name = "TERM_ID")
    private String termId;
    @Size(max = 4000)
    @Column(name = "ORIGINAL_DATA")
    private String originalData;
    @Size(max = 3)
    @Column(name = "REVERSED")
    private String reversed;
    @Column(name = "TNX_STAMP")
    private LocalDateTime tnxStamp;
    @Size(max = 300)
    @Column(name = "PACKAGER")
    private String packager;
    @Column(name = "AMOUNT")
    private Long amount;
    @Size(max = 69)
    @Column(name = "ACCOUNT_NO")
    private String accountNo;
    @Size(max = 18)
    @Column(name = "LOCAL_TIME")
    private String localTime;
    @Size(max = 12)
    @Column(name = "LOCAL_DATE")
    private String localDate;
    @Size(max = 12)
    @Column(name = "SETTLE_DATE")
    private String settleDate;
    @Size(max = 12)
    @Column(name = "MCC")
    private String mcc;
    @Size(max = 9)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Size(max = 69)
    @Column(name = "DEST_ACCOUNT")
    private String destAccount;
    @Column(name = "RECONCILE_TIME")
    private LocalDateTime reconcileTime;
    @Column(name = "TRANX_DATE")
    private LocalDateTime tranxDate;
    @Size(max = 765)
    @Column(name = "ADD_INFO")
    private String addInfo;
    @Size(max = 90)
    @Column(name = "SERVICE_CODE")
    private String serviceCode;
    @Size(max = 600)
    @Column(name = "VAS_INFO")
    private String vasInfo;
    @Size(max = 33)
    @Column(name = "BEN_ID")
    private String benId;
    @Size(max = 999)
    @Column(name = "IBFT_INFO")
    private String ibftInfo;
    @Column(name = "AMOUNT_SETTLEMENT")
    private Long amountSettlement;
    @Column(name = "AMOUNT_CARDHOLDER")
    private Long amountCardholder;
    @Size(max = 9)
    @Column(name = "CURRENCY_CODE_SETTLEMENT")
    private String currencyCodeSettlement;
    @Size(max = 9)
    @Column(name = "CURRENCY_CODE_CARDHOLDER")
    private String currencyCodeCardholder;
    @Size(max = 8)
    @Column(name = "CONVERSION_RATE_SETTLEMENT")
    private String conversionRateSettlement;
    @Size(max = 8)
    @Column(name = "CONVERSION_RATE_CARDHOLDER")
    private String conversionRateCardholder;
    @Size(max = 60)
    @Column(name = "USER_DEFINE")
    private String userDefine;
    @Size(max = 16)
    @Column(name = "TRANX_REF")
    private String tranxRef;
    @Size(max = 9)
    @Column(name = "ACQ_INST_COUNTRY_CODE")
    private String acqInstCountryCode;
    @Size(max = 6)
    @Column(name = "POS_ENTRY_MODE")
    private String posEntryMode;
    @Size(max = 6)
    @Column(name = "POS_CONDITION_CODE")
    private String posConditionCode;
    @Size(max = 15)
    @Column(name = "CARD_ACCEPT_ID_CODE")
    private String cardAcceptIdCode;
    @Size(max = 40)
    @Column(name = "CARD_ACCEPT_NAME_LOCATION")
    private String cardAcceptNameLocation;
    @Column(name = "SYNC_DATE")
    private LocalDateTime syncDate;
    @Column(name = "MODIF_DATE")
    private LocalDateTime modifDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SETTLE_BANK_CODE")
    private String settleBankCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MASTER_MERCHANT_CODE")
    private String masterMerchantCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MERCHANT_CODE")
    private String merchantCode;
    @Size(max = 20)
    @Column(name = "MERCHANT_BRANCH_CODE")
    private String merchantBranchCode;
    @Size(max = 20)
    @Column(name = "MERCHANT_CASHIER_CODE")
    private String merchantCashierCode;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "MERCHANT_TYPE")
    private EMerchantType merchantType;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 7)
    @Column(name = "ORDER_CODE")
    private String orderCode;
    @Column(name = "RESP_DATE")
    private LocalDateTime respDate;
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "SETTLE_BANK_ID")
    private Integer settleBankId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "MASTER_MERCHANT_ID")
    private Integer masterMerchantId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "MERCHANT_ID")
    private Long merchantId;
    
    @Column(name = "MERCHANT_BRANCH_ID")
    private Long merchantBranchId;
    
    @Column(name = "MERCHANT_CASHIER_ID")
    private Long merchantCashierId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PAYMENT_REFERENCE")
    private String paymentReference;

    public HisPayment() {
    }

    public HisPayment(Long id) {
        this.id = id;
    }

    public HisPayment(Long id, String settleBankCode, String masterMerchantCode, String merchantCode, EMerchantType merchantType) {
        this.id = id;
        this.settleBankCode = settleBankCode;
        this.masterMerchantCode = masterMerchantCode;
        this.merchantCode = merchantCode;
        this.merchantType = merchantType;
    }

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getOriginalDate() {
        return originalDate;
    }

    public void setOriginalDate(String originalDate) {
        this.originalDate = originalDate;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getAcqId() {
        return acqId;
    }

    public void setAcqId(String acqId) {
        this.acqId = acqId;
    }

    public String getIssId() {
        return issId;
    }

    public void setIssId(String issId) {
        this.issId = issId;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

    public String getReversed() {
        return reversed;
    }

    public void setReversed(String reversed) {
        this.reversed = reversed;
    }

    public LocalDateTime getTnxStamp() {
        return tnxStamp;
    }

    public void setTnxStamp(LocalDateTime tnxStamp) {
        this.tnxStamp = tnxStamp;
    }

    public String getPackager() {
        return packager;
    }

    public void setPackager(String packager) {
        this.packager = packager;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDestAccount() {
        return destAccount;
    }

    public void setDestAccount(String destAccount) {
        this.destAccount = destAccount;
    }

    public LocalDateTime getReconcileTime() {
        return reconcileTime;
    }

    public void setReconcileTime(LocalDateTime reconcileTime) {
        this.reconcileTime = reconcileTime;
    }

    public LocalDateTime getTranxDate() {
        return tranxDate;
    }

    public void setTranxDate(LocalDateTime tranxDate) {
        this.tranxDate = tranxDate;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getVasInfo() {
        return vasInfo;
    }

    public void setVasInfo(String vasInfo) {
        this.vasInfo = vasInfo;
    }

    public String getBenId() {
        return benId;
    }

    public void setBenId(String benId) {
        this.benId = benId;
    }

    public String getIbftInfo() {
        return ibftInfo;
    }

    public void setIbftInfo(String ibftInfo) {
        this.ibftInfo = ibftInfo;
    }

    public Long getAmountSettlement() {
        return amountSettlement;
    }

    public void setAmountSettlement(Long amountSettlement) {
        this.amountSettlement = amountSettlement;
    }

    public Long getAmountCardholder() {
        return amountCardholder;
    }

    public void setAmountCardholder(Long amountCardholder) {
        this.amountCardholder = amountCardholder;
    }

    public String getCurrencyCodeSettlement() {
        return currencyCodeSettlement;
    }

    public void setCurrencyCodeSettlement(String currencyCodeSettlement) {
        this.currencyCodeSettlement = currencyCodeSettlement;
    }

    public String getCurrencyCodeCardholder() {
        return currencyCodeCardholder;
    }

    public void setCurrencyCodeCardholder(String currencyCodeCardholder) {
        this.currencyCodeCardholder = currencyCodeCardholder;
    }

    public String getConversionRateSettlement() {
        return conversionRateSettlement;
    }

    public void setConversionRateSettlement(String conversionRateSettlement) {
        this.conversionRateSettlement = conversionRateSettlement;
    }

    public String getConversionRateCardholder() {
        return conversionRateCardholder;
    }

    public void setConversionRateCardholder(String conversionRateCardholder) {
        this.conversionRateCardholder = conversionRateCardholder;
    }

    public String getUserDefine() {
        return userDefine;
    }

    public void setUserDefine(String userDefine) {
        this.userDefine = userDefine;
    }

    public String getTranxRef() {
        return tranxRef;
    }

    public void setTranxRef(String tranxRef) {
        this.tranxRef = tranxRef;
    }

    public String getAcqInstCountryCode() {
        return acqInstCountryCode;
    }

    public void setAcqInstCountryCode(String acqInstCountryCode) {
        this.acqInstCountryCode = acqInstCountryCode;
    }

    public String getPosEntryMode() {
        return posEntryMode;
    }

    public void setPosEntryMode(String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }

    public String getPosConditionCode() {
        return posConditionCode;
    }

    public void setPosConditionCode(String posConditionCode) {
        this.posConditionCode = posConditionCode;
    }

    public String getCardAcceptIdCode() {
        return cardAcceptIdCode;
    }

    public void setCardAcceptIdCode(String cardAcceptIdCode) {
        this.cardAcceptIdCode = cardAcceptIdCode;
    }

    public String getCardAcceptNameLocation() {
        return cardAcceptNameLocation;
    }

    public void setCardAcceptNameLocation(String cardAcceptNameLocation) {
        this.cardAcceptNameLocation = cardAcceptNameLocation;
    }

    public LocalDateTime getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(LocalDateTime syncDate) {
        this.syncDate = syncDate;
    }

    public LocalDateTime getModifDate() {
        return modifDate;
    }

    public void setModifDate(LocalDateTime modifDate) {
        this.modifDate = modifDate;
    }

    public String getSettleBankCode() {
        return settleBankCode;
    }

    public void setSettleBankCode(String settleBankCode) {
        this.settleBankCode = settleBankCode;
    }

    public String getMasterMerchantCode() {
        return masterMerchantCode;
    }

    public void setMasterMerchantCode(String masterMerchantCode) {
        this.masterMerchantCode = masterMerchantCode;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantBranchCode() {
        return merchantBranchCode;
    }

    public void setMerchantBranchCode(String merchantBranchCode) {
        this.merchantBranchCode = merchantBranchCode;
    }

    public String getMerchantCashierCode() {
        return merchantCashierCode;
    }

    public void setMerchantCashierCode(String merchantCashierCode) {
        this.merchantCashierCode = merchantCashierCode;
    }

    public EMerchantType getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(EMerchantType merchantType) {
        this.merchantType = merchantType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public LocalDateTime getRespDate() {
        return respDate;
    }

    public void setRespDate(LocalDateTime respDate) {
        this.respDate = respDate;
    }

    public Integer getSettleBankId() {
        return settleBankId;
    }

    public void setSettleBankId(Integer settleBankId) {
        this.settleBankId = settleBankId;
    }

    public Integer getMasterMerchantId() {
        return masterMerchantId;
    }

    public void setMasterMerchantId(Integer masterMerchantId) {
        this.masterMerchantId = masterMerchantId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMerchantBranchId() {
        return merchantBranchId;
    }

    public void setMerchantBranchId(Long merchantBranchId) {
        this.merchantBranchId = merchantBranchId;
    }

    public Long getMerchantCashierId() {
        return merchantCashierId;
    }

    public void setMerchantCashierId(Long merchantCashierId) {
        this.merchantCashierId = merchantCashierId;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
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
        if (!(object instanceof HisPayment)) {
            return false;
        }
        HisPayment other = (HisPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.napas.achoffline.reportoffline.entity.HisPayment[ id=" + id + " ]";
    }
    
}
