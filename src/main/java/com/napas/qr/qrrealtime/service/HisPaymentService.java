/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.AmountOfMoney;
import com.napas.qr.qrrealtime.define.EMerchantType;
import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.SettlementStatusDef;
import com.napas.qr.qrrealtime.entity.HisPayment;
import com.napas.qr.qrrealtime.entity.TblMasterMerchant;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import com.napas.qr.qrrealtime.entity.TblMerchantPersonal;
import com.napas.qr.qrrealtime.models.MerchantInfo;
import com.napas.qr.qrrealtime.models.PaymentNotify;
import com.napas.qr.qrrealtime.repository.HisPaymentRepository;
import com.napas.qr.qrrealtime.security.services.SDEService;
import com.napas.qr.qrrealtime.security.services.UserDetailsImpl;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author huynx
 */
@Service
@Slf4j
public class HisPaymentService {
    @Autowired
    private HisPaymentRepository hisPaymentDAO;
    
    @Autowired
    private SDEService sdeService;
    
    private PaymentNotify fromEntity(HisPayment payment) {
        PaymentNotify msg = new PaymentNotify();

        msg.setMerchantType(payment.getMerchantType());
        msg.setNarration(payment.getNarration());
        msg.setOrderCode(payment.getOrderCode());
        msg.setPaymentAcceptionStatus(payment.getTransactionStatus());
        msg.setTime(payment.getAcceptDatetime());

        AmountOfMoney amount = new AmountOfMoney();
        amount.setValue(payment.getTransactionAmount());
        amount.setCurrencyCode(payment.getCurrencyCode());
        msg.setAmount(amount);

        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setCreditorAccount(payment.getDestAccount());
        merchantInfo.setMasterMerchantCode(payment.getMasterMerchantCode());
        merchantInfo.setMerchantBranchCode(payment.getMerchantBranchCode());
        merchantInfo.setMerchantCashierCode(payment.getMerchantCashierCode());
        merchantInfo.setMerchantCode(payment.getMerchantCode());
        merchantInfo.setSettleBankCode(payment.getSettleBankCode());

        try {
            String merchantInfoEncrypted = sdeService.encryptAndSign(merchantInfo);
            msg.setMerchantInfo(merchantInfoEncrypted);
        } catch(Exception ex) {
            log.error("Loi:" + ex.getMessage());
        }
        
        
        return msg;
    }
    
    public ResponseEntity<?> search(Pageable paging,
            LocalDateTime beginDate,
            LocalDateTime endDate,
            Long branchId,
            Long cashierId,
            SettlementStatusDef status) {
        
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        ETargetType targetType = userDetails.getTargetType();
        
        if(targetType == ETargetType.PERSONAL) {
            return findPaymentMerchantPersonal(paging, userDetails, beginDate, endDate, status);
        } else {
            return findPaymentMerchantCorporate(paging, userDetails, beginDate, endDate, branchId, cashierId, status);
        }
    }
    
    public ResponseEntity<?> findPaymentMerchantCorporate(Pageable paging,
            UserDetailsImpl userDetails,
            LocalDateTime beginDate,
            LocalDateTime endDate,
            Long branchId,
            Long cashierId,
            SettlementStatusDef status) {
        
        
        TblMasterMerchant masterMerchant = userDetails.getMasterMerchant();
        TblMerchantCorporate merchant = userDetails.getMerchant();

        Page<HisPayment> listPayment = hisPaymentDAO.findPaymentType2(paging, 
                beginDate, 
                endDate, 
                masterMerchant.getId(), 
                EMerchantType.CORPORATE,
                merchant.getId(), 
                branchId, 
                cashierId, 
                status);
        
        return ResponseEntity.ok(listPayment.map(p -> fromEntity(p)));
    }
    
    private ResponseEntity<?> findPaymentMerchantPersonal(
            Pageable paging,
            UserDetailsImpl userDetails,
            LocalDateTime beginDate,
            LocalDateTime endDate,
            SettlementStatusDef status
    ) {
        TblMerchantPersonal personal = userDetails.getMerchantPersonal();
        Long masterMerchantId = personal.getTblMasterMerchant().getId();
        Long merchantId = personal.getId();
        Page<HisPayment>  listPayment = hisPaymentDAO.findPaymentType2(paging, beginDate, endDate, 
                masterMerchantId, EMerchantType.PERSONAL, merchantId, null, null, status);
        return ResponseEntity.ok(listPayment.map(p -> fromEntity(p)));
    }
}
