package com.napas.qr.repository;

import com.napas.qr.entity.Provice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviceRepository extends JpaRepository<Provice,Long> {
}
