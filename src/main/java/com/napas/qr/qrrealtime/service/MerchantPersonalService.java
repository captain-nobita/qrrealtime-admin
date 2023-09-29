package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.ERole;
import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import com.napas.qr.qrrealtime.entity.TblDistrict;
import com.napas.qr.qrrealtime.entity.TblMasterMerchant;
import com.napas.qr.qrrealtime.entity.TblMerchantPersonal;
import com.napas.qr.qrrealtime.entity.TblSettleBank;
import com.napas.qr.qrrealtime.models.CreateMerchantPersonalDTO;
import com.napas.qr.qrrealtime.models.CreatedMerchantCorporateDTO;
import com.napas.qr.qrrealtime.models.TblMerchantPersonalDTO;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.DistrictRepository;
import com.napas.qr.qrrealtime.repository.MerchantPersonalRepository;
import com.napas.qr.qrrealtime.repository.SettleBankRepository;
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
public class MerchantPersonalService extends BaseService {

    @Autowired
    private MerchantPersonalRepository merchantPersonalRepository;

    @Autowired
    private SettleBankRepository settleBankRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Value("${redirectPvCombank}")
    private String redirectPvCombank;



    @Autowired
    private ModelMapper modelMapper;


    private TblMerchantPersonalDTO fromEntity(TblMerchantPersonal entity) {
        TblMerchantPersonalDTO dto = modelMapper.map(entity, TblMerchantPersonalDTO.class);
        dto.setDistrictId(entity.getTblDistrict().getId());
        dto.setDistrictName(entity.getTblDistrict().getDistrictName());
        dto.setProvName(entity.getTblDistrict().getTblProvince().getProvName());
        dto.setProvId(entity.getTblDistrict().getTblProvince().getId());
        dto.setSettleBankId(entity.getTblSettleBank().getId());
        return dto;
    }

    public ResponseEntity<?> search(Pageable paging, String name, MerchantStatus status, String merchantCode) {
        if (getUserDetails().getTargetType().equals(ETargetType.MASTER)){
            Page<TblMerchantPersonal> dbResult = merchantPersonalRepository.search(paging, name, status, merchantCode, getUserDetails().getMasterMerchant().getId(),null);
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        }
        else if (getUserDetails().getTargetType().equals(ETargetType.PERSONAL)){
            Page<TblMerchantPersonal> dbResult = merchantPersonalRepository.search(paging, name, status, merchantCode, getUserDetails().getMasterMerchant().getId(),getTargetId());
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền xem thông tin merchant này"));
        }
    }

    public ResponseEntity<?> post(CreateMerchantPersonalDTO input) {

        TblMerchantPersonal tblMerchantPersonal = new TblMerchantPersonal();
        if (getUserDetails().getTargetType() == ETargetType.PERSONAL && getERole().equals(ERole.ADMIN) || getUserDetails().getTargetType() == ETargetType.MASTER) {
            tblMerchantPersonal.setName(input.getName());
            if (input.getMerchantCode().length()>5 ||input.getMerchantCode().length()<5 ){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("MerchantCode là chuỗi gồm 5 kí tự"));
            }
            TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
            if (merchantPersonalRepository.existsByMerchantCodeAndTblMasterMerchant(input.getMerchantCode(), masterMerchant)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Mã Code đã tồn tại"));
            }
            tblMerchantPersonal.setMerchantCode(input.getMerchantCode());
            if (input.getDistrictId() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập đủ thông tin địa chỉ"));

            }
            TblDistrict district = districtRepository.findById(input.getDistrictId()).orElse(null);

            tblMerchantPersonal.setTblMasterMerchant(masterMerchant);
            tblMerchantPersonal.setTblDistrict(district);
            tblMerchantPersonal.setAddressLine(input.getAddressLine());
            tblMerchantPersonal.setOwnerName(input.getOwnerName());
            tblMerchantPersonal.setEmailAddress(input.getEmailAddress());
            if (input.getSettleBankId() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa điền thông tin ngân hàng thụ hưởng"));
            }
            TblSettleBank settleBank = settleBankRepository.findById(input.getSettleBankId()).orElse(null);
            tblMerchantPersonal.setTblSettleBank(settleBank);
            if (input.getCreditorAccount().length()>20){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Số tài khoản không vượt quá 20 kí tự"));
            }
            if (input.getCreditorAccount().length()<8){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Số tài khoản không nhỏ hơn 8 kí tự"));
            }
            tblMerchantPersonal.setCreditorAccount(input.getCreditorAccount());
            tblMerchantPersonal.setCreatedByUser(getUserId());
            tblMerchantPersonal.setDateCreated(new Date());
            tblMerchantPersonal.setPhoneNumber(input.getPhoneNumber());
            tblMerchantPersonal.setStatus(MerchantStatus.APPROVED);
            tblMerchantPersonal.setTblMasterMerchant(masterMerchant);
            tblMerchantPersonal.setPaymentAcceptanceStatus(PaymentAcceptStatus.READY);

            TblMerchantPersonal savedData = merchantPersonalRepository.save(tblMerchantPersonal);
            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền tạo Merchant này"));
        }
    }



    public ResponseEntity<?>put(Long id, CreateMerchantPersonalDTO input){
        if (getUserDetails().getTargetType() == ETargetType.PERSONAL && getERole().equals(ERole.ADMIN) || getUserDetails().getTargetType() == ETargetType.MASTER) {
            TblMerchantPersonal tblMerchantPersonal =merchantPersonalRepository.findById(id).orElse(null);
            tblMerchantPersonal.setName(input.getName());
            TblDistrict district = districtRepository.findById(input.getDistrictId()).orElse(null);
            tblMerchantPersonal.setTblDistrict(district);
            tblMerchantPersonal.setAddressLine(input.getAddressLine());
            tblMerchantPersonal.setOwnerName(input.getOwnerName());
            tblMerchantPersonal.setEmailAddress(input.getEmailAddress());
            TblSettleBank settleBank = settleBankRepository.findById(input.getSettleBankId()).orElse(null);
            tblMerchantPersonal.setTblSettleBank(settleBank);
            if (input.getCreditorAccount().length()>20){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Số tài khoản không vượt quá 20 kí tự"));
            }
            if (input.getCreditorAccount().length()<8){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Số tài khoản không nhỏ hơn 8 kí tự"));
            }
            tblMerchantPersonal.setCreditorAccount(input.getCreditorAccount());
            tblMerchantPersonal.setModifiedByUser(getUserId());
            tblMerchantPersonal.setPhoneNumber(input.getPhoneNumber());
            tblMerchantPersonal.setDateModified(new Date());
            tblMerchantPersonal.setStatus(MerchantStatus.APPROVED);
            tblMerchantPersonal.setPaymentAcceptanceStatus(PaymentAcceptStatus.READY);

            TblMerchantPersonal savedData = merchantPersonalRepository.save(tblMerchantPersonal);
            return ResponseEntity.ok(fromEntity(savedData));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền Sửa Merchant này"));

        }

    }


    public ResponseEntity<?> delete(Long id) {
        if (getUserDetails().getTargetType() == ETargetType.PERSONAL && getERole().equals(ERole.ADMIN) || getUserDetails().getTargetType() == ETargetType.MASTER && getERole().equals(ERole.ADMIN)) {
            TblMerchantPersonal merchant = merchantPersonalRepository.findById(id).orElse(null);
            merchant.setStatus(MerchantStatus.DELETED);
            merchantPersonalRepository.save(merchant);
            return ResponseEntity.ok(new MessageResponse("Delete merchant"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền Xóa Merchant này"));

        }
    }
    public ResponseEntity<?> merchantDetail(Long id) {

        TblMerchantPersonal merchant = merchantPersonalRepository.findById(id).orElse(null);

        if (merchant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Không tồn tại Merchant cá nhân này"));
        }
        return ResponseEntity.ok(fromEntity(merchant));
    }

    public ResponseEntity<?>getPatch(){

        return ResponseEntity.ok(redirectPvCombank);
    }

    public ResponseEntity<?> list() {
        TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
        if (getUserDetails().getTargetType().equals(ETargetType.MASTER)) {
            List<TblMerchantPersonal> list = merchantPersonalRepository.get(masterMerchant.getId(), null, MerchantStatus.APPROVED);
            return ResponseEntity.ok(list);
        } else if (getUserDetails().getTargetType().equals(ETargetType.PERSONAL)) {
            List<TblMerchantPersonal> list = merchantPersonalRepository.get(masterMerchant.getId(), getTargetId(), MerchantStatus.APPROVED);
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Không tồn tại Master Merchant này"));
        }
    }
    public ResponseEntity<?> checkCode(CreateMerchantPersonalDTO input){
        TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
        if (merchantPersonalRepository.existsByMerchantCodeAndTblMasterMerchant(input.getMerchantCode(),masterMerchant)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Mã Code đã tồn tại"));
        }
        return ResponseEntity.ok("Thành Công");
    }
}
