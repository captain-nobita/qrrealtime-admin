package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import com.napas.qr.qrrealtime.models.TblMerchantCorporateDTO;
import com.napas.qr.qrrealtime.repository.MerchantCorporateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantCorporateService {

    @Autowired
    private MerchantCorporateRepository merchantCorporateRepository;

    @Autowired
    private ModelMapper modelMapper;


    private TblMerchantCorporateDTO fromEntity(TblMerchantCorporate entity) {
        TblMerchantCorporateDTO dto = modelMapper.map(entity, TblMerchantCorporateDTO.class);
        return dto;
    }

    public ResponseEntity<?> search(Pageable paging, String name, MerchantStatus status, String merchantCode) {
        Page<TblMerchantCorporate> dbResult  = merchantCorporateRepository.search(paging,name,status,merchantCode);
        return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
    }
}
