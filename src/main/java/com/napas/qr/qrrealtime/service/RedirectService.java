package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import com.napas.qr.qrrealtime.entity.TblMerchantPersonal;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.MerchantBranchRepository;
import com.napas.qr.qrrealtime.repository.MerchantCorporateRepository;
import com.napas.qr.qrrealtime.repository.MerchantPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RedirectService extends BaseService {

    @Autowired
    private MerchantBranchRepository merchantBranchRepository;

    @Autowired
    private MerchantPersonalRepository merchantPersonalRepository;

    @Autowired
    private MerchantCorporateRepository merchantCorporateRepository;

    public ResponseEntity<?> get(ETargetType targetType, Long targetId, String account){
        if (targetType.equals(ETargetType.MERCHANT)){
            TblMerchantCorporate merchantCorporate = merchantCorporateRepository.findById(targetId).orElse(null);
            merchantCorporate.setCreditorAccount(account);
            merchantCorporateRepository.save(merchantCorporate);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Update Số tài khoản Merchant thành công"));
        }else if (targetType.equals(ETargetType.PERSONAL)){
            TblMerchantPersonal merchantPersonal = merchantPersonalRepository.findById(targetId).orElse(null);
            merchantPersonal.setCreditorAccount(account);
            merchantPersonalRepository.save(merchantPersonal);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Update Số tài khoản Merchant cá nhân thành công"));
        }else if (targetType.equals(ETargetType.BRANCH)){
            TblMerchantBranch merchantBranch = merchantBranchRepository.findById(targetId).orElse(null);
            merchantBranch.setCreditorAccount(account);
            merchantBranchRepository.save(merchantBranch);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Update Số tài khoản Branch thành công"));
        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không truyền đủ thông tin"));

    }
}
