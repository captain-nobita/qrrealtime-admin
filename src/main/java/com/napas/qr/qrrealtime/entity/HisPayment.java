/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.napas.qr.qrrealtime.entity;


import com.napas.qr.qrrealtime.define.AccountTypeDef;
import com.napas.qr.qrrealtime.define.EMerchantType;
import com.napas.qr.qrrealtime.define.SettlementStatusDef;
import com.napas.qr.qrrealtime.define.SystemDirectionDef;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
import lombok.Data;

/**
 *
 * @author huynx
 */
@Data
@Entity
@Table(name = "HIS_PAYMENT")
@NamedQueries({
    @NamedQuery(name = "HisPayment.findAll", query = "SELECT h FROM HisPayment h")})
public class HisPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCEPT_DATETIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime acceptDatetime;
    @Column(name = "RESPONSE_DATETIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime responseDatetime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODIF_DATETIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifDatetime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPORTED_DATETIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime importedDatetime;
    @Size(max = 30)
    @Column(name = "TRANS_DATE_TIME")
    private String transDateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_0200")
    private long id0200;
    @Column(name = "ID_0210")
    private Long id0210;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "SYSTEM_DIRECTION")
    private SystemDirectionDef systemDirection;
    @Size(max = 57)
    @Column(name = "PAN")
    private String pan;
    @Size(max = 69)
    @Column(name = "FROM_ACCOUNT")
    private String fromAccount;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "FROM_ACCOUNT_TYPE")
    private AccountTypeDef fromAccountType;
    @Size(max = 765)
    @Column(name = "FROM_ACCOUNT_NAME")
    private String fromAccountName;
    @Size(max = 765)
    @Column(name = "FROM_ACCOUNT_ADDRESS_LINE")
    private String fromAccountAddressLine;
    @Size(max = 69)
    @Column(name = "DEST_ACCOUNT")
    private String destAccount;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "DEST_ACCOUNT_TYPE")
    private AccountTypeDef destAccountType;
    @Size(max = 100)
    @Column(name = "DEST_ACCOUNT_NAME")
    private String destAccountName;
    @Size(max = 999)
    @Column(name = "NARRATION")
    private String narration;
    @Size(max = 18)
    @Column(name = "PROC_CODE")
    private String procCode;
    @Size(max = 90)
    @Column(name = "SERVICE_CODE")
    private String serviceCode;
    @Size(max = 12)
    @Column(name = "MCC")
    private String mcc;
    @Size(max = 60)
    @Column(name = "CHANNEL_ID")
    private String channelId;
    @Size(max = 18)
    @Column(name = "TRACE_NO")
    private String traceNo;
    @Size(max = 16)
    @Column(name = "TRANSACTION_REFERENCE")
    private String transactionReference;
    @Size(max = 8)
    @Column(name = "SHORTED_TRANSACTION_REFERENCE")
    private String shortedTransactionReference;
    @Size(max = 36)
    @Column(name = "REF_NO")
    private String refNo;
    @Size(max = 24)
    @Column(name = "TERM_ID")
    private String termId;
    @Size(max = 33)
    @Column(name = "ACQ_ID")
    private String acqId;
    @Size(max = 33)
    @Column(name = "ISS_ID")
    private String issId;
    @Size(max = 33)
    @Column(name = "BEN_ID")
    private String benId;
    @Size(max = 18)
    @Column(name = "APPROVAL_CODE")
    private String approvalCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "RESPONSE_CODE")
    private String responseCode;
    
    @Size(max = 9)
    @Column(name = "CREDITOR_RESPONSE_CODE")
    private String creditorResponseCode;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_STATUS")
    private SettlementStatusDef transactionStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;
    @Size(max = 9)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "LOCAL_TIME")
    private String localTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "LOCAL_DATE")
    private String localDate;
    @Column(name = "LOCAL_DATE_TIME", columnDefinition = "TIMESTAMP")
    private LocalDateTime localDateTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "SETTLE_DATE")
    private String settleDate;
    @Column(name = "SETTLE_DATE_TIME", columnDefinition = "DATE")
    private LocalDate settleDateTime;
    @Column(name = "SETTLEMENT_AMOUNT")
    private BigDecimal settlementAmount;
    @Size(max = 20)
    @Column(name = "SETTLEMENT_CURRENCY_CODE")
    private String settlementCurrencyCode;
    @Size(max = 20)
    @Column(name = "SETTLEMENT_CONVERSION_RATE")
    private String settlementConversionRate;
    @Column(name = "SETTLEMENT_CODE")
    private Integer settlementCode;
    @Column(name = "CARDHOLDER_AMOUNT")
    private BigDecimal cardholderAmount;
    @Size(max = 9)
    @Column(name = "CARDHOLDER_CURRENCY_CODE")
    private String cardholderCurrencyCode;
    @Size(max = 20)
    @Column(name = "CARDHOLDER_CONVERSION_RATE")
    private String cardholderConversionRate;
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


    @Size(max = 9)
    @Column(name = "ACQ_INST_COUNTRY_CODE")
    private String acqInstCountryCode;
    
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
    
    @Size(max = 7)
    @Column(name = "ORDER_CODE")
    private String orderCode;

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
        return "com.napas.ach.ibftbackendapi.entity.HisPayment[ id=" + id + " ]";
    }
    
}
