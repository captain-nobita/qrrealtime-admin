package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMasterMerchant;
import com.napas.qr.qrrealtime.entity.TblMerchantPersonal;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.MasterMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterMerchantService extends BaseService{

    @Autowired
    private MasterMerchantRepository masterMerchantRepository;

    public ResponseEntity<?> list(){
        TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
        if (masterMerchant != null){
            List<TblMasterMerchant> list = masterMerchantRepository.get(masterMerchant.getId(), MerchantStatus.APPROVED);
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Không tồn tại Master Merchant này"));
    }
}
