package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.TblSettleBank;
import com.napas.qr.qrrealtime.models.SettleBankDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettleBankRepository extends JpaRepository<TblSettleBank, Long> {

 @Query("SELECT new com.napas.qr.qrrealtime.models.SettleBankDTO(" +
         " S.id," +
         " S.bankId," +
         " B.bankShortName) from TblSettleBank S left join TblNapasBank B on S.bankId = B.bankId")
    public List<SettleBankDTO> listBank();
}
