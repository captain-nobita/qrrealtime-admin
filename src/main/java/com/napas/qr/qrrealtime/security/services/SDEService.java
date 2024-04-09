/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.napas.qr.qrrealtime.security.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class SDEService {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Autowired
    private ObjectMapper objectMapper;

    private static final int POOL = (2 << 6) - 1;

    private class ParnerEncryptorAndVerifierEngine {

        private RSAEncrypter encrypter;
        private RSASSAVerifier verifier;

        public RSAEncrypter getEncrypter() {
            return encrypter;
        }

        public void setEncrypter(RSAEncrypter encrypter) {
            this.encrypter = encrypter;
        }

        public RSASSAVerifier getVerifier() {
            return verifier;
        }

        public void setVerifier(RSASSAVerifier verifier) {
            this.verifier = verifier;
        }
    }

    @Autowired
    private SDEKeyStoreService ksService;

    private final ParnerEncryptorAndVerifierEngine[] achEngine = new ParnerEncryptorAndVerifierEngine[POOL];
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    private final JWSSigner[] mySigner = new JWSSigner[POOL];
    private final RSADecrypter[] myDecrypter = new RSADecrypter[POOL];

    @PostConstruct
    @DependsOn("MyKeyStoreService")
    private void init() throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        initMyEngine();
        logger.info("Khoi tao SDE engine cho PIS thanh cong");
        
        initNapasModule();
        logger.info("Khoi tao SDE engine voi ACH thanh cong");
        
    }

    private void initMyEngine() throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        for (int i = 0; i < POOL; i++) {
            mySigner[i] = new RSASSASigner(ksService.getMyPrivateKey());
            myDecrypter[i] = new RSADecrypter(ksService.getMyPrivateKey());
        }
    }

    private void initNapasModule() throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        RSAPublicKey napasModulePublicKey = ksService.getNapasModulePublicKey();

        for (int i = 0; i < POOL; i++) {
            RSAEncrypter encrypter = new RSAEncrypter(napasModulePublicKey);
            RSASSAVerifier verifier = new RSASSAVerifier(napasModulePublicKey);

            ParnerEncryptorAndVerifierEngine engine = new ParnerEncryptorAndVerifierEngine();
            engine.setEncrypter(encrypter);
            engine.setVerifier(verifier);

            achEngine[i] = engine;
        }
    }

    

    private RSAEncrypter getEncrypterNapas() {
        int idx = (int) (Thread.currentThread().getId() & POOL);
        return achEngine[idx].encrypter;
    }

    private RSADecrypter getDecrypter() {
        int idx = (int) (Thread.currentThread().getId() & POOL);
        return myDecrypter[idx];
    }
    
    private RSASSAVerifier getVerifierNapas() {
        int idx = (int) (Thread.currentThread().getId() & POOL);
        return achEngine[idx].verifier;
    }
    
    private JWSSigner getSigner() {
        int idx = (int) (Thread.currentThread().getId() & POOL);
        return mySigner[idx];
    }

    public String encryptAndSign(Object input) throws KeyStoreException, JOSEException, NoSuchAlgorithmException, UnrecoverableKeyException, JsonProcessingException {
        
        String clearText = objectMapper.writeValueAsString(input);
        
        JWEHeader jweHeader = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM);

        Payload senderJwePayload = new Payload(clearText);
        JWEObject jweObject = new JWEObject(jweHeader, senderJwePayload);

        // Create an RSA encrypted with the specified public RSA key
        RSAEncrypter encrypter = getEncrypterNapas();

        // Doing the actual encryption
        jweObject.encrypt(encrypter);

        String senderJwe = jweObject.serialize();

        JWSSigner signer = getSigner();
        JWSHeader header = new JWSHeader(JWSAlgorithm.RS512);

        Payload pl = new Payload(senderJwe);
        JWSObject jwsObject = new JWSObject(header, pl);
        jwsObject.sign(signer);

        String senderJws = jwsObject.serialize();
        return senderJws;
    }

    public String verifyAndDecrypt(String senderJws) throws ParseException, KeyStoreException, JOSEException, NoSuchAlgorithmException, UnrecoverableKeyException {
        String paJson = null;
        JWSObject jwsObject = JWSObject.parse(senderJws);
        RSASSAVerifier verifier = getVerifierNapas();
        if (jwsObject.verify(verifier)) {
            JWEObject jweObject = JWEObject.parse(jwsObject.getPayload().toString());
            RSADecrypter decrypter = getDecrypter();
            jweObject.decrypt(decrypter);
            if (jweObject.getState() == JWEObject.State.DECRYPTED) {
                paJson = jweObject.getPayload().toString();
            } else {
                logger.error("Loi decrypt thong tin nhay cam gui");
            }
        } else {
            logger.error("Loi verify thong tin nhay cam gui boi");
        }
        return paJson;
    }
}
