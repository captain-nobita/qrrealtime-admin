/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.napas.qr.qrrealtime.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.napas.qr.qrrealtime.define.ECreateBankAccountRequestStatus;

/**
 *
 * @author huynx
 */
public class HisCreateBankAccountRequestDTO {
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accountNumber;

    private String bankName;
    
    private ECreateBankAccountRequestStatus status;
    
    private int settleBankId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public int getSettleBankId() {
        return settleBankId;
    }

    public void setSettleBankId(int settleBankId) {
        this.settleBankId = settleBankId;
    }

    
}
