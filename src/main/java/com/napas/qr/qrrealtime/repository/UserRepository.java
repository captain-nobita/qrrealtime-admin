package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
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


    Boolean existsByUsername(String username);


    @Query("SELECT T FROM TblOrgUser T WHERE " +
            " (T.fullname like %:fullname% or :fullname is null) " +
            " AND (T.status= :status or :status is null) " +
            " AND(T.username like %:username% or :username is null)" +
            " AND(T.createdByUser =:userId or T.targetId =:targetId)" +
            " AND(T.status<>'DELETED') ")
    Page<TblOrgUser> search(Pageable pageable,
                            @Param("fullname") String fullname,
                            @Param("status") MerchantStatus status,
                            @Param("username") String username,
                            @Param("userId") Long userId,
                            @Param("targetId")Long targetId);

    @Query("SELECT T FROM TblOrgUser T WHERE " +
            " (T.fullname like %:fullname% or :fullname is null) " +
            " AND (T.status= :status or :status is null) " +
            " AND(T.username like %:username% or :username is null)")
    Page<TblOrgUser> searchAdmin(Pageable pageable,
                            @Param("fullname") String fullname,
                            @Param("status") MerchantStatus status,
                            @Param("username") String username);
}
