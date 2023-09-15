package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.TblSettleBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleBankRepository extends JpaRepository<TblSettleBank, Long> {
}
