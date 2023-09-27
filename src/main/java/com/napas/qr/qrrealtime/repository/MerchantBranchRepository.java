package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantBranchRepository extends JpaRepository<TblMerchantBranch, Long> {

    Boolean existsByBranchCodeAndTblMerchantCorporate(String BranchCode, TblMerchantCorporate merchantCorporate);

    TblMerchantBranch findFirstByTblMerchantCorporate(TblMerchantCorporate merchantCorporate);

    @Query("SELECT T FROM TblMerchantBranch T WHERE " +
            " (T.name like %:name% or :name is null) " +
            " AND (T.status= :status or :status is null) " +
            " AND(T.branchCode like %:branchCode% or :branchCode is null)" +
            " AND(T.tblMerchantCorporate.id= :merchantId or :merchantId is null)" +
            " AND(T.id= :targetId or :targetId is null)" +
            " AND(T.tblMerchantCorporate.tblMasterMerchant.id = :MasterMerchantId or :MasterMerchantId is null )" +
            " AND(T.status<>'DELETED') ")
    Page<TblMerchantBranch> search(Pageable pageable,
                                   @Param("name") String name,
                                   @Param("status") MerchantStatus status,
                                   @Param("branchCode") String branchCode,
                                   @Param("merchantId") Long merchantId,
                                   @Param("targetId") Long targetId,
                                   @Param("MasterMerchantId") Long MasterMerchantId);

    @Query("SELECT T FROM TblMerchantBranch T WHERE " +
            " (T.tblMerchantCorporate.id= :merchantId or :merchantId is null)" +
            " AND(T.id= :targetId or :targetId is null)" +
            " AND(T.status<>'DELETED') ")
    List<TblMerchantBranch> get(@Param("merchantId") Long merchantId,
                                @Param("targetId") Long targetId);
}
