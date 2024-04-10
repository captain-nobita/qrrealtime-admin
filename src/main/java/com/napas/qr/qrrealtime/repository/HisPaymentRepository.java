/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.define.EMerchantType;
import com.napas.qr.qrrealtime.define.SettlementStatusDef;
import com.napas.qr.qrrealtime.entity.HisPayment;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author huynx
 */
public interface HisPaymentRepository extends JpaRepository<HisPayment, Long> {
    @Query("""
SELECT t FROM HisPayment t WHERE 
           tnxStamp between :beginDate and :endDate
           AND masterMerchantCode = :masterMerchantCode
           AND merchantCode = :merchantCode
           AND (:branchCode is null or t.merchantBranchCode = :branchCode)
           AND (:cashierCode is null or t.merchantCashierCode = :cashierCode)
           AND (:status is null or t.transactionStatus = :status)
           """)
    public Page<HisPayment> findPayment(Pageable paging,
            LocalDateTime beginDate,
            LocalDateTime endDate,
            String masterMerchantCode,
            String merchantCode,
            String branchCode,
            String cashierCode,
            SettlementStatusDef status
    );
    
    @Query("""
SELECT t FROM HisPayment t WHERE 
           tnxStamp between :beginDate and :endDate
           AND masterMerchantId = :masterMerchantId
           AND merchantType = :merchantType
           AND merchantId = :merchantId
           AND (:branchId is null or t.merchantBranchId = :branchId)
           AND (:cashierId is null or t.merchantCashierId = :cashierId)
           AND (:status is null or t.transactionStatus = :status)
           """)
    public Page<HisPayment> findPaymentType2(Pageable paging,
            LocalDateTime beginDate,
            LocalDateTime endDate,
            Long masterMerchantId,
            EMerchantType merchantType,
            Long merchantId,
            Long branchId,
            Long cashierId,
            SettlementStatusDef status
    );
}
