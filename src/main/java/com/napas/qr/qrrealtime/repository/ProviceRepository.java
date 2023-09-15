package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.Provice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProviceRepository extends JpaRepository<Provice,Long> {

    @Query("select P from Provice P where P.provName like %:provName%  or :provName is null")
    List<Provice> searchProvice(@Param("provName") String provName);
}
