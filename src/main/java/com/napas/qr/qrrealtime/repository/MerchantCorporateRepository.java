package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantCorporateRepository extends JpaRepository<TblMerchantCorporate,Long> {

    @Query("SELECT T FROM TblMerchantCorporate T WHERE " +
            " (T.name = :name or :name is null) " +
            " AND (T.status=:status or :status is null) " +
            " AND(T.merchantCode=:merchantCode or :merchantCode is null) ")
    Page<TblMerchantCorporate>search(Pageable pageable,
                                        @Param("name") String name,
                                        @Param("status") MerchantStatus status,
                                        @Param("merchantCode") String merchantCode);
}
