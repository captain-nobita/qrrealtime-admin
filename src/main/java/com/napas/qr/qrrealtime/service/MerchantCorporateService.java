package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.*;
import com.napas.qr.qrrealtime.entity.*;
import com.napas.qr.qrrealtime.models.CreatedMerchantCorporateDTO;
import com.napas.qr.qrrealtime.models.TblMerchantCorporateDTO;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.DistrictRepository;
import com.napas.qr.qrrealtime.repository.MasterMerchantRepository;
import com.napas.qr.qrrealtime.repository.MerchantCorporateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MerchantCorporateService extends BaseService {

    @Autowired
    private MerchantCorporateRepository merchantCorporateRepository;

    @Autowired
    private MasterMerchantRepository masterMerchantRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Value("${redirectPvCombankcorporate}")
    private String redirectPvCombank;

    @Autowired
    private ModelMapper modelMapper;


    private TblMerchantCorporateDTO fromEntity(TblMerchantCorporate entity) {
        TblMerchantCorporateDTO dto = modelMapper.map(entity, TblMerchantCorporateDTO.class);
        dto.setDistrictId(entity.getTblDistrict().getId());
        dto.setProvId(entity.getTblDistrict().getTblProvince().getId());
        dto.setDistrictName(entity.getTblDistrict().getDistrictName());
        dto.setProvName(entity.getTblDistrict().getTblProvince().getProvName());
        return dto;
    }

    public ResponseEntity<?> search(Pageable paging, String name, MerchantStatus status, String merchantCode) {
        if (getUserDetails().getTargetType().equals(ETargetType.MASTER)){
            TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
            Page<TblMerchantCorporate> dbResult  = merchantCorporateRepository.search(paging,name,status,merchantCode, masterMerchant.getId(),null);
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        } else if (getUserDetails().getTargetType().equals(ETargetType.MERCHANT)){
            TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
            Page<TblMerchantCorporate> dbResult  = merchantCorporateRepository.search(paging,name,status,merchantCode, masterMerchant.getId(),getTargetId());
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền xem Merchant này"));
        }
    }

    public ResponseEntity<?> post(CreatedMerchantCorporateDTO input) {

        TblMerchantCorporate tblMerchantCorporate = new TblMerchantCorporate();
        if (getUserDetails().getTargetType() == ETargetType.MASTER) {
            if (input.getName()== null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Tên Merchant không được để trống"));
            }
            tblMerchantCorporate.setName(input.getName());
            if (merchantCorporateRepository.existsByMerchantCodeAndTblMasterMerchant(input.getMerchantCode(), getUserDetails().getMasterMerchant())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("MerchantCode đã tồn tại"));
            }
            if (input.getMerchantCode().length()>3 || input.getMerchantCode().length()<3){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("MerchantCode là chuỗi gồm 3 kí tự"));
            }
            tblMerchantCorporate.setMerchantCode(input.getMerchantCode());
            TblDistrict district = districtRepository.findById(input.getDistrictId()).orElse(null);
            tblMerchantCorporate.setTblDistrict(district);
            tblMerchantCorporate.setAddressLine(input.getAddressLine());
            tblMerchantCorporate.setCreatedByUser(getUserId());
            tblMerchantCorporate.setPhoneNumber(input.getPhoneNumber());
            tblMerchantCorporate.setDateCreated(new Date());
            tblMerchantCorporate.setStatus(MerchantStatus.APPROVED);
            TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
            tblMerchantCorporate.setTblMasterMerchant(masterMerchant);
            tblMerchantCorporate.setBranchAccountSettledType(EBranchAccountSettledType.INDIVIDUALLY);
            tblMerchantCorporate.setTblSettleBank(null);
            if (input.getDkkd() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập mã Đăng Kí Kinh Doanh"));
            }
            tblMerchantCorporate.setDkkd(input.getDkkd());
            tblMerchantCorporate.setTaxNumber(input.getTaxNumber());
            tblMerchantCorporate.setPaymentAcceptanceStatus(PaymentAcceptStatus.READY);
            tblMerchantCorporate.setWebhook(input.getWebhook());

            TblMerchantCorporate savedData = merchantCorporateRepository.save(tblMerchantCorporate);
            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền tạo Merchant này"));

        }
    }
    public ResponseEntity<?> put(Long id, CreatedMerchantCorporateDTO input) {

        TblMerchantCorporate tblMerchantCorporate = merchantCorporateRepository.findById(id).orElse(null);
        if (getUserDetails().getTargetType() == ETargetType.MASTER) {
            if (input.getName()== null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Tên Merchant không được để trống"));
            }
            tblMerchantCorporate.setName(input.getName());
            TblDistrict district = districtRepository.findById(input.getDistrictId()).orElse(null);
            tblMerchantCorporate.setTblDistrict(district);
            tblMerchantCorporate.setAddressLine(input.getAddressLine());
            tblMerchantCorporate.setPhoneNumber(input.getPhoneNumber());
            tblMerchantCorporate.setModifiedByUser(getUserId());
            tblMerchantCorporate.setDateModified(new Date());
            tblMerchantCorporate.setStatus(MerchantStatus.APPROVED);
            TblMasterMerchant masterMerchant = masterMerchantRepository.findById(getTargetId()).orElse(null);
            tblMerchantCorporate.setTblMasterMerchant(masterMerchant);
            if (input.getDkkd() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập mã Đăng Kí Kinh Doanh"));
            }
            tblMerchantCorporate.setDkkd(input.getDkkd());
            tblMerchantCorporate.setTaxNumber(input.getTaxNumber());
            tblMerchantCorporate.setWebhook(input.getWebhook());
            TblMerchantCorporate savedData = merchantCorporateRepository.save(tblMerchantCorporate);

            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền sửa Merchant này"));

        }
    }
    public ResponseEntity<?> delete(Long id) {
        if (getUserDetails().getTargetType() == ETargetType.MASTER) {
            TblMerchantCorporate merchant = merchantCorporateRepository.findById(id).orElse(null);
            merchant.setStatus(MerchantStatus.DELETED);
            merchantCorporateRepository.save(merchant);
            return ResponseEntity.ok(new MessageResponse("Delete merchant"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền xóa Merchant này"));
    }

    public ResponseEntity<?> merchantDetail(Long id) {

        TblMerchantCorporate merchant = merchantCorporateRepository.findById(id).orElse(null);

        if (merchant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Không tồn tại Merchant cá nhân này"));
        }
        return ResponseEntity.ok(fromEntity(merchant));
    }

    public ResponseEntity<?>getPatch(){

        return ResponseEntity.ok(redirectPvCombank);
    }

    public ResponseEntity<?> list(){
        TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
        if (getUserDetails().getTargetType().equals(ETargetType.MASTER)){
            List<TblMerchantCorporate> list = merchantCorporateRepository.get(masterMerchant.getId(), null);
            return ResponseEntity.ok(list);
        } else if (getUserDetails().getTargetType().equals(ETargetType.MERCHANT)){
            List<TblMerchantCorporate> list = merchantCorporateRepository.get(masterMerchant.getId(),getTargetId());
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Không tồn tại Master Merchant này"));
    }
}
