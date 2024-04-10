package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.models.HisCreateBankAccountRequestDTO;
import com.napas.qr.qrrealtime.service.PartnerBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/PartnerBankAccount")
public class PartnerBankAccountController {

    @Autowired
    private PartnerBankAccountService partnerBankAccountService;

    @PostMapping("/storeNewBankAccount")
    public ResponseEntity<?> storeNewBankAccount(@RequestBody HisCreateBankAccountRequestDTO request){
        return partnerBankAccountService.storeNewBankAccount(request);
    }
    
    @GetMapping("/createNewBankAccountRequest")
    public ResponseEntity<?> createNewBankAccountRequest(){
        return partnerBankAccountService.createNewBankAccountRequest();
    }
    
    @GetMapping("/getRequestInfo/{id}")
    public ResponseEntity<?> getRequestInfo(@PathVariable String id){
        return partnerBankAccountService.getRequestInfo(id);
    }
}
