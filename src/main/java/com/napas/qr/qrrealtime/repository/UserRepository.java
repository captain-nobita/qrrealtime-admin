package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMasterMerchant;
import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import com.napas.qr.qrrealtime.entity.TblOrgUser;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<TblOrgUser, Long> {

    Optional<TblOrgUser> findByUsername(String username);

    @Query("SELECT T FROM TblOrgUser T WHERE " +
            " (T.fullname like %:fullname% or :fullname is null) " +
            " AND (T.status= :status or :status is null) " +
            " AND(T.username like %:username% or :username is null)" +
            " AND(T.targetId =:targetId or :targetId is null )" +
            " OR(T.targetId = :idTarget or :idTarget is null )" +
            " AND(T.status<>'DELETED') ")
    Page<TblOrgUser> search(Pageable pageable,
                            @Param("fullname") String fullname,
                            @Param("status") MerchantStatus status,
                            @Param("username") String username,
                            @Param("targetId")Long targetId,
                            @Param("idTarget") Long idTarget);

    @Query("SELECT T FROM TblOrgUser T WHERE " +
            " (T.fullname like %:fullname% or :fullname is null) " +
            " AND (T.status= :status or :status is null) " +
            " AND(T.username like %:username% or :username is null)" +
            " AND(T.targetId =:targetId or :targetId is null )" +
            " AND(T.status<>'DELETED') ")
    Page<TblOrgUser> searchUserCashierAndPersonal(Pageable pageable,
                            @Param("fullname") String fullname,
                            @Param("status") MerchantStatus status,
                            @Param("username") String username,
                            @Param("targetId")Long targetId);
    
    @Query("""
SELECT
    u
FROM
    TblOrgUser u
WHERE
     (u.fullname like %:fullname% or :fullname is null)
     AND (u.status= :status or :status is null)
     AND(u.username like %:username% or :username is null)
     AND (( u.targetType = 'BRANCH'
             AND u.targetId = :branchId )
         OR (u.targetType = 'CASHIER'
             AND EXISTS (
                 SELECT
                     1
                 FROM
                     TblMerchantCashier tmc
                 WHERE
                         tmc.tblMerchantBranch = :branch
                     AND u.targetId = tmc.id)
    ))
           """)
    Page<TblOrgUser> searchForBranch(Pageable pageable,
                            @Param("fullname") String fullname,
                            @Param("status") MerchantStatus status,
                            @Param("username") String username,
                            @Param("branchId") Long branchId,
                            @Param("branch") TblMerchantBranch branch);
    
    @Query(
"""
SELECT
    u
FROM
    TblOrgUser u
WHERE
     (u.fullname like %:fullname% or :fullname is null)
     AND (u.status= :status or :status is null)
     AND (u.username like %:username% or :username is null)
     AND (( u.targetType = 'MASTER'
             AND u.targetId = :masterMerchantId )
         OR (u.targetType = 'MERCHANT'
             AND EXISTS (
                 SELECT
                     1
                 FROM
                     TblMerchantCorporate tmc
                 WHERE
                         tmc.tblMasterMerchant = :masterMerchant
                     AND u.targetId = tmc.id))
          OR (u.targetType = 'PERSONAL'
              AND EXISTS (
                  SELECT
                      1
                  FROM
                      TblMerchantPersonal tmp
                  WHERE
                          tmp.tblMasterMerchant = :masterMerchant
                      AND u.targetId = tmp.id))
              )
"""
    )
    Page<TblOrgUser> searchForMaster(Pageable pageable,
                            @Param("fullname") String fullname,
                            @Param("status") MerchantStatus status,
                            @Param("username") String username,
                            @Param("masterMerchantId") Long masterMerchantId,
                            @Param("masterMerchant") TblMasterMerchant masterMerchant);
    
    @Query("""
SELECT
    u
FROM
    TblOrgUser u
WHERE
     (u.fullname like %:fullname% or :fullname is null)
     AND (u.status= :status or :status is null)
     AND(u.username like %:username% or :username is null)
     AND (( u.targetType = 'MERCHANT'
             AND u.targetId = :merchantId )
         OR (u.targetType = 'BRANCH'
             AND EXISTS (
                 SELECT
                     1
                 FROM
                     TblMerchantBranch tmb
                 WHERE
                         tmb.tblMerchantCorporate = :merchant
                     AND u.targetId = tmb.id)
    ))
           """)
    Page<TblOrgUser> searchForMerchantCorporate(Pageable pageable,
                            @Param("fullname") String fullname,
                            @Param("status") MerchantStatus status,
                            @Param("username") String username,
                            @Param("merchantId") Long merchantId,
                            @Param("merchant") TblMerchantCorporate merchant);
    
@Query("""
SELECT
    u
FROM
    TblOrgUser u
WHERE
     (u.fullname like %:fullname% or :fullname is null)
     AND (u.status= :status or :status is null)
     AND(u.username like %:username% or :username is null)
     AND ( u.targetType = 'CASHIER'
             AND u.targetId = :cashierId )
           """)
    Page<TblOrgUser> searchForCashier(Pageable pageable,
                            @Param("fullname") String fullname,
                            @Param("status") MerchantStatus status,
                            @Param("username") String username,
                            @Param("cashierId") Long cashierId);
    
@Query("""
SELECT
    u
FROM
    TblOrgUser u
WHERE
     (u.fullname like %:fullname% or :fullname is null)
     AND (u.status= :status or :status is null)
     AND(u.username like %:username% or :username is null)
     AND ( u.targetType = 'PERSONAL'
             AND u.targetId = :merchantId )
           """)
    Page<TblOrgUser> searchForPersonal(Pageable pageable,
                            @Param("fullname") String fullname,
                            @Param("status") MerchantStatus status,
                            @Param("username") String username,
                            @Param("merchantId") Long merchantId);
}
