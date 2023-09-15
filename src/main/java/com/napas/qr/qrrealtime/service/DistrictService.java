package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.entity.District;
import com.napas.qr.qrrealtime.models.DistrictDTO;
import com.napas.qr.qrrealtime.repository.DistrictRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ModelMapper mapper;


    private DistrictDTO fromEntity(District entity) {
        DistrictDTO dto = mapper.map(entity, DistrictDTO.class);
        return dto;
    }

    public List<DistrictDTO> get(Long provId){
        List<District> district = districtRepository.findByProvId(provId);
        return district.stream().map(entity -> fromEntity(entity)).collect(Collectors.toList());
    }
}
