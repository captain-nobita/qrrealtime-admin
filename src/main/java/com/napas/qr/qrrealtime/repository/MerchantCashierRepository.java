package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import com.napas.qr.qrrealtime.entity.TblMerchantCashier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantCashierRepository extends JpaRepository<TblMerchantCashier, Long> {

    Boolean existsByCashierCode(String CashierCode);

    @Query("SELECT T FROM TblMerchantCashier T WHERE " +
            " (T.cashierCode like %:cashierCode% or :cashierCode is null) " +
            " AND (T.status= :status or :status is null) " +
            " AND(T.tblMerchantBranch.id= :branchId or :branchId is null)" +
            " AND(T.id = :targetId)" +
            " AND(T.status<>'DELETED') ")
    Page<TblMerchantCashier> search(Pageable pageable,
                                    @Param("cashierCode") String cashierCode,
                                    @Param("status") MerchantStatus status,
                                    @Param("branchId") Long branchId,
                                    @Param("targetId") Long targetId);

    @Query("SELECT T FROM TblMerchantCashier T WHERE " +
            " (T.tblMerchantBranch.id= :branchId or :branchId is null)" +
            " AND(T.status<>'DELETED') ")
    List<TblMerchantCashier> list(
            @Param("branchId") Long branchId);
}
