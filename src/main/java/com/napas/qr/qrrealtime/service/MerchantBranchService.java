package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import com.napas.qr.qrrealtime.models.TblMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.TblMerchantCorporateDTO;
import com.napas.qr.qrrealtime.repository.MerchantBranchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantBranchService {

    @Autowired
    private MerchantBranchRepository merchantBranchRepository;

    @Autowired
    private ModelMapper modelMapper;


    private TblMerchantBranchDTO fromEntity(TblMerchantBranch entity) {
        TblMerchantBranchDTO dto = modelMapper.map(entity, TblMerchantBranchDTO.class);
        return dto;
    }

    public ResponseEntity<?> search(Pageable paging, String branchName, MerchantStatus status, String branchCode) {
        Page<TblMerchantBranch> dbResult  = merchantBranchRepository.search(paging,branchName,status,branchCode);
        return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
    }
}
