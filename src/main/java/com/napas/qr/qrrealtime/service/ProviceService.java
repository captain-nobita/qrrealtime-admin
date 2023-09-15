package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.entity.Provice;
import com.napas.qr.qrrealtime.models.ProviceDTO;
import com.napas.qr.qrrealtime.repository.ProviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviceService {

    @Autowired
    private ProviceRepository proviceRepository;


    @Autowired
    private ModelMapper mapper;


    private ProviceDTO fromEntity(Provice entity) {
        ProviceDTO dto = mapper.map(entity, ProviceDTO.class);
        return dto;
    }

    public List<ProviceDTO> get(String provName){
        List<Provice> provices = proviceRepository.searchProvice(provName);
        return provices.stream().map(entity -> fromEntity(entity)).collect(Collectors.toList());
    }
}
