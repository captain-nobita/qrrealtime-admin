/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.napas.qr.qrrealtime.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 *
 * @author huynx
 */
@Component
@Profile("test")
public class TestJKSConfigImpl implements JKSConfig {
    @Value("${app.signature.keystore-password}")
    private String signaturePassword;
    
    @Value("${app.sce.keystore-password}")
    private String sdePassword;

    @Override
    public String getSignaturePassword() {
        return signaturePassword;
    }

    @Override
    public String getSdePassword() {
        return sdePassword;
    }
}
