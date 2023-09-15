package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.repository.MasterMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterMerchantService {

    @Autowired
    private MasterMerchantRepository masterMerchantRepository;
}
