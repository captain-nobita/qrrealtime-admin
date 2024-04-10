/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.napas.qr.qrrealtime.security.services;

import com.napas.qr.qrrealtime.common.AppConfig;
import com.napas.qr.qrrealtime.config.JKSConfig;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class SDEKeyStoreService {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Value("${app.sce.keystore-file}")
    private String keystoreFile;
    
    @Autowired
    private JKSConfig jksConfig;

    @Autowired
    private AppConfig appConfig;
    
    private KeyStore keystore;
    
    @PostConstruct
    private void initKS() throws FileNotFoundException, KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        logger.info("Bat dau khoi tao SDE Keystore");
        FileInputStream is = new FileInputStream(keystoreFile);
        keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(is, jksConfig.getSdePassword().toCharArray());
        
        logger.info("Khoi tao xong SDE Keystore cho cac thanh phan NAPAS");
    }

    public RSAPrivateKey getMyPrivateKey() throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        return (RSAPrivateKey) keystore.getKey(appConfig.getNapasKeyId(), jksConfig.getSdePassword().toCharArray());
    }

    public RSAPublicKey getMyPublicKey() throws KeyStoreException {
        Certificate cert = keystore.getCertificate(appConfig.getNapasKeyId());

        return (RSAPublicKey) cert.getPublicKey();
    }

    public RSAPublicKey getNapasModulePublicKey() throws KeyStoreException {
        Certificate cert = keystore.getCertificate(appConfig.getMerchantAgentId());

        return (RSAPublicKey) cert.getPublicKey();
    }
}
