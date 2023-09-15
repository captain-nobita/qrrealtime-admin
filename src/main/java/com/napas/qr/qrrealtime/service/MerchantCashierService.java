package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.repository.MerchantCashierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantCashierService {

    @Autowired
    private MerchantCashierRepository merchantCashierRepository;
}
