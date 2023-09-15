package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.TblOrgUser;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<TblOrgUser, Long> {

    Optional<TblOrgUser> findByUsername(String username);

    Boolean existsByUsername(String username);
}
