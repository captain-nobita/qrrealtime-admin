/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.napas.qr.qrrealtime.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 *
 * @author huynx
 */
@Data
public class MerchantInfo {
    private String creditorAccount;
    private String settleBankCode;
    private String masterMerchantCode;
    private String merchantCode;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String merchantBranchCode;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String merchantCashierCode;
}
