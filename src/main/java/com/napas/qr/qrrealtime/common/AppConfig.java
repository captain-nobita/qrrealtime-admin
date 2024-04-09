/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.napas.qr.qrrealtime.common;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author huynx
 */
@Configuration
@Data
public class AppConfig {
    private String napasKeyId = "napas";
    private String merchantAgentId = "971133";
}
