package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantBranchRepository extends JpaRepository<TblMerchantBranch, Long> {
}
