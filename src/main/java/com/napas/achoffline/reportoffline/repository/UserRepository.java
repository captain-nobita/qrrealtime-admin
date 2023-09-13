package com.napas.achoffline.reportoffline.repository;

import com.napas.achoffline.reportoffline.entity.TblOrgUser;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<TblOrgUser, Long> {

    Optional<TblOrgUser> findByUsername(String username);

    Boolean existsByUsername(String username);
}
