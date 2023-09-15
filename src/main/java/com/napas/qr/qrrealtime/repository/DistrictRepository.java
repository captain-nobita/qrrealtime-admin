package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Long> {

    List<District> findByProvId(Long provId);
}
