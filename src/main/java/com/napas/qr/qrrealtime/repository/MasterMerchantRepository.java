package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.TblMasterMerchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterMerchantRepository extends JpaRepository<TblMasterMerchant, Long> {
}
