package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.service.VietQR;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/VietQR")
public class QrCodeController {
    @Autowired
    private VietQR vietQR;

    @Operation(summary = "generateQRCode", description = "QR Code của merchant cá nhân", tags = {"Personal"})
    @GetMapping("/generateQRCode")
    public void generateQRCode(HttpServletResponse response){
        vietQR.generateQRCode(response);
    }

    @Operation(summary = "generateQRCodeCashier", description = "QR Code của merchant đại lý bán hàng ", tags = {"Cashier"})
    @GetMapping("/generateQRCodeCashier")
    public void generateQRCodeCashier(HttpServletResponse response,@RequestParam("cashierId") Long cashierId){
        vietQR.generateQRCodeCashier(response, cashierId);
    }
}
