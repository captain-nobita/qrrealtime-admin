package com.napas.qr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableJpaRepositories
public class QRMerchantRealTimeApplication {
    public static void main(String[] args) {
        SpringApplication.run(QRMerchantRealTimeApplication.class, args);
    }
}
