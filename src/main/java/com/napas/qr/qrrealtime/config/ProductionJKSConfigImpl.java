/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.napas.qr.qrrealtime.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 *
 * @author huynx
 */
@Component
@Profile("!test")
public class ProductionJKSConfigImpl implements JKSConfig {
    private final String signaturePassword = "kOmr02si";
    
    private final String sdePassword = "cFtxUi1L";

    @Override
    public String getSignaturePassword() {
        return signaturePassword;
    }

    @Override
    public String getSdePassword() {
        return sdePassword;
    }
}
