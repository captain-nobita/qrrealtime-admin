/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.napas.qr.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author huynx
 */
@Configuration
public class AppConfig {

    @Value("${napas.ach.offline.show-full-pan:false}")
    private boolean showFullPAN;

    public boolean isShowFullPAN() {
        return showFullPAN;
    }
}
