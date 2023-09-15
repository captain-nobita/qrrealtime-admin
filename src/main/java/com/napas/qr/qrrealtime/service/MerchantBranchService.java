package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.repository.MerchantBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantBranchService {

    @Autowired
    private MerchantBranchRepository merchantBranchRepository;
}
