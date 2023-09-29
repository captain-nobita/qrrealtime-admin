/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.napas.qr.qrrealtime.repository;

import com.napas.qr.qrrealtime.entity.HisCreateBankAccountRequest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author huynx
 */
public interface HisCreateBankAccountRequestDAO extends JpaRepository<HisCreateBankAccountRequest, String> {
    public Optional<HisCreateBankAccountRequest> findByIdAndCreatedBy(String id, long createdBy);
}
