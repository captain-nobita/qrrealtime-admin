package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantPersonal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantPersonalRepository extends JpaRepository<TblMerchantPersonal, Long> {

    Boolean existsByMerchantCode(String merchantCode);

    @Query("SELECT T FROM TblMerchantPersonal T WHERE " +
            " (T.name like %:name% or :name is null) " +
            " AND (T.status=:status or :status is null) " +
            " AND(T.merchantCode=:merchantCode or :merchantCode is null)" +
            " AND(T.tblMasterMerchant.id = :id or :id is null)" +
            " AND(T.createdByUser = :userId or T.id = :targetId)" +
            " AND(T.status <> 'DELETED') ")
    Page<TblMerchantPersonal> search(Pageable pageable,
                                     @Param("name") String name,
                                     @Param("status") MerchantStatus status,
                                     @Param("merchantCode") String merchantCode,
                                     @Param("id") Long id,
                                     @Param("userId") Long userId,
                                     @Param("targetId")Long targetId);


    @Query("SELECT T FROM TblMerchantPersonal T WHERE " +
            " (T.tblMasterMerchant.id = :id or :id is null) " +
            " AND(T.status = :status or :status is null) ")
    List<TblMerchantPersonal> get(
            @Param("id") Long id,
            @Param("status") MerchantStatus status);
}
