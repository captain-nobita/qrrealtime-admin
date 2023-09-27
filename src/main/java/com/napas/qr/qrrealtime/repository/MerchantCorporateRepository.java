package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMasterMerchant;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantCorporateRepository extends JpaRepository<TblMerchantCorporate, Long> {
    Boolean existsByMerchantCodeAndTblMasterMerchant(String merchantCode, TblMasterMerchant masterMerchant);

    TblMerchantCorporate findFirstByTblMasterMerchant(TblMasterMerchant masterMerchant);

    @Query("SELECT T FROM TblMerchantCorporate T WHERE " +
            " (T.name like %:name% or :name is null) " +
            " AND (T.status= :status or :status is null) " +
            " AND(T.merchantCode like %:merchantCode% or :merchantCode is null)" +
            " AND(T.tblMasterMerchant.id= :masterMerchantId or :masterMerchantId is null)" +
            " AND(T.id= :targetId or :targetId is null )" +
            " AND(T.status<>'DELETED') ")
    Page<TblMerchantCorporate> search(Pageable pageable,
                                      @Param("name") String name,
                                      @Param("status") MerchantStatus status,
                                      @Param("merchantCode") String merchantCode,
                                      @Param("masterMerchantId") Long masterMerchantId,
                                      @Param("targetId") Long targetId);


    @Query("SELECT T FROM TblMerchantCorporate T WHERE " +
            " (T.tblMasterMerchant.id= :masterMerchantId or :masterMerchantId is null)" +
            " AND(T.id= :targetId or :targetId is null)" +
            " AND(T.status<>'DELETED') ")
    List<TblMerchantCorporate> get(
            @Param("masterMerchantId") Long masterMerchantId,
            @Param("targetId") Long targetId);
}
