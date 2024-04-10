package com.napas.qr.qrrealtime.controllers;


import com.napas.qr.qrrealtime.models.SettleBankDTO;
import com.napas.qr.qrrealtime.service.SettleBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value = "/api/settleBank", produces = "application/json")
public class SettleBankController {

    @Autowired
    private SettleBankService settleBankService;

    @GetMapping()
    public List<SettleBankDTO> search() {
        return settleBankService.get();
    }
}
