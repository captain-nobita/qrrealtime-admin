package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.service.VietQR;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/mngweb/api/VietQR")
@Slf4j
public class QrCodeController {
    @Autowired
    private VietQR vietQR;

    @GetMapping("/generateQRCode")
    public void generateQRCode(HttpServletResponse response){
        log.info("Tiep nhan yeu cau sinh qrcode");
        vietQR.generateQRCode(response);
    }
    @GetMapping("/generateQRCodeCashier")
    public void generateQRCodeCashier(HttpServletResponse response,@RequestParam("cashierId") Long cashierId){
        log.info("Tiep nhan yeu cau sinh qrcode");
        vietQR.generateQRCodeCashier(response, cashierId);
    }
}
