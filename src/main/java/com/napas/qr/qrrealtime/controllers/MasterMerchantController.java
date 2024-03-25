package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.service.MasterMerchantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/masterMerchant", produces = "application/json")
public class MasterMerchantController {

    @Autowired
    private MasterMerchantService masterMerchantService;

    @Operation(summary = "Search", description = "Lấy danh sách các Master Merchant", tags = {"MasterMerchant"})
    @GetMapping()
    public ResponseEntity<?> list(){
        return masterMerchantService.list();
    }
}
