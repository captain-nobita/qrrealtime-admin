package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.TblMerchantCashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantCashierRepository extends JpaRepository<TblMerchantCashier, Long> {
}
