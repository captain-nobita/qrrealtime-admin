package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.service.VietQR;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/mngweb/api/VietQR")
public class QrCodeController {
    @Autowired
    private VietQR vietQR;

    @GetMapping("/generateQRCode")
    public void generateQRCode(HttpServletResponse response){
        vietQR.generateQRCode(response);
    }
    @GetMapping("/generateQRCodeCashier")
    public void generateQRCodeCashier(HttpServletResponse response){
        vietQR.generateQRCodeCashier(response);
    }
}
