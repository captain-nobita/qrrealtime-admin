package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.TblNapasBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NapasBankRepository extends JpaRepository<TblNapasBank, Long> {

    TblNapasBank findByBankId(String bankId);
}
