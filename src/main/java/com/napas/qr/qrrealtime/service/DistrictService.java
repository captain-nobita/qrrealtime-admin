package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.entity.TblDistrict;
import com.napas.qr.qrrealtime.entity.TblProvince;
import com.napas.qr.qrrealtime.models.DistrictDTO;
import com.napas.qr.qrrealtime.repository.DistrictRepository;
import com.napas.qr.qrrealtime.repository.ProviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProviceRepository proviceRepository;

    @Autowired
    private ModelMapper mapper;


    private DistrictDTO fromEntity(TblDistrict entity) {
        DistrictDTO dto = mapper.map(entity, DistrictDTO.class);
        return dto;
    }

    public List<DistrictDTO> get(Long provId){
        TblProvince province = proviceRepository.findById(provId).orElse(null);
        List<TblDistrict> district = districtRepository.findByTblProvince(province);
        return district.stream().map(entity -> fromEntity(entity)).collect(Collectors.toList());
    }
}
