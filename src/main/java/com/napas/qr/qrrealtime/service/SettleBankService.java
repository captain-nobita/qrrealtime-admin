package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.entity.TblSettleBank;
import com.napas.qr.qrrealtime.models.SettleBankDTO;
import com.napas.qr.qrrealtime.repository.SettleBankRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettleBankService {

    @Autowired
    private SettleBankRepository settleBankRepository;

    @Autowired
    private ModelMapper mapper;

    private SettleBankDTO fromEntity(TblSettleBank entity) {
        SettleBankDTO dto = mapper.map(entity, SettleBankDTO.class);
        return dto;
    }

    public List<SettleBankDTO> get(){
        List<TblSettleBank> settleBanks = settleBankRepository.findAll();
        return settleBanks.stream().map(entity -> fromEntity(entity)).collect(Collectors.toList());
    }
}
