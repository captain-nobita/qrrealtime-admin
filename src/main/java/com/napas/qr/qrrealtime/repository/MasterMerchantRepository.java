package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMasterMerchant;
import com.napas.qr.qrrealtime.entity.TblMerchantPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterMerchantRepository extends JpaRepository<TblMasterMerchant, Long> {

    @Query("SELECT T FROM TblMasterMerchant T WHERE " +
            " (T.id = :id or :id is null) " +
            " AND(T.status = :status or :status is null) ")
    List<TblMasterMerchant> get(
            @Param("id") Long id,
            @Param("status") MerchantStatus status);
}
