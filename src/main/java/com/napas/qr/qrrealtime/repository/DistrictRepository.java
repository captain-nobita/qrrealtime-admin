package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.TblDistrict;
import com.napas.qr.qrrealtime.entity.TblProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<TblDistrict,Long> {

   List<TblDistrict> findByTblProvince(TblProvince province);
}
