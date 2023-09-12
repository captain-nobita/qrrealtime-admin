package com.napas.qr.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class IsoMessageDTO implements Serializable {

    private String mti;
    private String cardNo;
    private String procCode;
    private Long amount;
    private Long amountSettlement;
    private String traceNo;
    private String settleDate;
    private String acqId;
    private String refNo;
    private String approvalCode;
    private String responseCode;
    private String termId;
    private String originalDate;
    private String userDefine;
    private String serviceCode;
    private String tranxRef;
    private String benId;
    private String accountNo;
    private String destAccount;
    private String ibftInfo;
    private String merchantName;
    private String deptName;


    public IsoMessageDTO(String mti, String cardNo, String procCode, Long amount, Long amountSettlement, String traceNo, String settleDate, String acqId, String refNo, String approvalCode, String responseCode, String termId, String originalDate, String userDefine, String serviceCode, String tranxRef, String benId, String accountNo, String destAccount, String ibftInfo, String merchantName, String deptName) {
        this.mti = mti;
        this.cardNo = cardNo;
        this.procCode = procCode;
        this.amount = amount;
        this.amountSettlement = amountSettlement;
        this.traceNo = traceNo;
        this.settleDate = settleDate;
        this.acqId = acqId;
        this.refNo = refNo;
        this.approvalCode = approvalCode;
        this.responseCode = responseCode;
        this.termId = termId;
        this.originalDate = originalDate;
        this.userDefine = userDefine;
        this.serviceCode = serviceCode;
        this.tranxRef = tranxRef;
        this.benId = benId;
        this.accountNo = accountNo;
        this.destAccount = destAccount;
        this.ibftInfo = ibftInfo;
        this.merchantName = merchantName;
        this.deptName = deptName;

    }
}
