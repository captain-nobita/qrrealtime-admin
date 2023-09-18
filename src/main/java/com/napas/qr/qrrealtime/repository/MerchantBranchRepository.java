package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantBranchRepository extends JpaRepository<TblMerchantBranch, Long> {

    @Query("SELECT T FROM TblMerchantBranch T WHERE " +
            " (T.branchName = :branchName or :branchName is null) " +
            " AND (T.status= :status or :status is null) " +
            " AND(T.branchCode= :branchCode or :branchCode is null)" +
            " AND(T.status<>'DELETED') ")
    Page<TblMerchantBranch> search(Pageable pageable,
                                      @Param("branchName") String branchName,
                                      @Param("status") MerchantStatus status,
                                      @Param("branchCode") String branchCode);

}
