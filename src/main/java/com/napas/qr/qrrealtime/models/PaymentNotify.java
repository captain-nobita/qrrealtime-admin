/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.napas.qr.qrrealtime.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.napas.qr.qrrealtime.define.AmountOfMoney;
import com.napas.qr.qrrealtime.define.EMerchantType;
import com.napas.qr.qrrealtime.define.SettlementStatusDef;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author huynx
 */
@Data
public class PaymentNotify {
    private SettlementStatusDef paymentAcceptionStatus;
    private AmountOfMoney amount;
    private LocalDateTime time;
    private String narration;
    private EMerchantType merchantType;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String orderCode;
    
    private String merchantInfo;
}
