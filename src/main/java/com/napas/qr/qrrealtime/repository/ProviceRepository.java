package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.TblProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProviceRepository extends JpaRepository<TblProvince,Long> {

    @Query("select P from TblProvince P where P.provName like %:provName%  or :provName is null")
    List<TblProvince> searchProvice(@Param("provName") String provName);
}
