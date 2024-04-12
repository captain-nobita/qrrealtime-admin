/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.napas.qr.qrrealtime.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * @author huynx
 */
@ConfigurationProperties(prefix = "app")
@Component
public class AppConfig {
    private String napasKeyId = "napas";
    private String merchantAgentId = "971133";

    public String getNapasKeyId() {
        return napasKeyId;
    }

    public void setNapasKeyId(String napasKeyId) {
        this.napasKeyId = napasKeyId;
    }

    public String getMerchantAgentId() {
        return merchantAgentId;
    }

    public void setMerchantAgentId(String merchantAgentId) {
        this.merchantAgentId = merchantAgentId;
    }
    
    
}
