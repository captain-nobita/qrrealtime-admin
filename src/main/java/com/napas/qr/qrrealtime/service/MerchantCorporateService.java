package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.EBranchAccountSettledType;
import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import com.napas.qr.qrrealtime.entity.*;
import com.napas.qr.qrrealtime.models.CreatedMerchantCorporateDTO;
import com.napas.qr.qrrealtime.models.TblMerchantCorporateDTO;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.DistrictRepository;
import com.napas.qr.qrrealtime.repository.MasterMerchantRepository;
import com.napas.qr.qrrealtime.repository.MerchantCorporateRepository;
import com.napas.qr.qrrealtime.repository.SettleBankRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MerchantCorporateService extends BaseService {

    @Autowired
    private MerchantCorporateRepository merchantCorporateRepository;

    @Autowired
    private MasterMerchantRepository masterMerchantRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private SettleBankRepository settleBankRepository;

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

    public ResponseEntity<?> post(CreatedMerchantCorporateDTO input) {

        TblMerchantCorporate tblMerchantCorporate = new TblMerchantCorporate();
        if (getUserDetails().getTargetType() == ETargetType.MASTER) {
            tblMerchantCorporate.setName(input.getName());
            if (merchantCorporateRepository.existsByMerchantCode(input.getMerchantCode())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("MerchantCode đã tồn tại"));
            }
            tblMerchantCorporate.setMerchantCode(input.getMerchantCode());
            TblDistrict district = districtRepository.findById(input.getDistrictId()).orElse(null);
            tblMerchantCorporate.setTblDistrict(district);
            tblMerchantCorporate.setAddressLine(input.getAddressLine());
            TblSettleBank settleBank = settleBankRepository.findById(input.getSettleBankId()).orElse(null);
            tblMerchantCorporate.setTblSettleBank(settleBank);
            tblMerchantCorporate.setCreditorAccount(input.getCreditorAccount());
            tblMerchantCorporate.setCreatedByUser(getUserId());
            tblMerchantCorporate.setPhoneNumber(input.getPhoneNumber());
            tblMerchantCorporate.setDateCreated(new Date());
            tblMerchantCorporate.setStatus(MerchantStatus.APPROVED);
            TblMasterMerchant masterMerchant = masterMerchantRepository.findById(getTargetId()).orElse(null);
            tblMerchantCorporate.setTblMasterMerchant(masterMerchant);
            tblMerchantCorporate.setBranchAccountSettledType(EBranchAccountSettledType.CENTRALIZED);
            tblMerchantCorporate.setPaymentAcceptanceStatus(PaymentAcceptStatus.READY);

            TblMerchantCorporate savedData = merchantCorporateRepository.save(tblMerchantCorporate);
            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền tạo Merchant này"));

        }
    }
    public ResponseEntity<?> put(Long id, CreatedMerchantCorporateDTO input) {

        TblMerchantCorporate tblMerchantCorporate = merchantCorporateRepository.findById(id).orElse(null);
        if (getUserDetails().getTargetType() == ETargetType.MASTER) {
            tblMerchantCorporate.setName(input.getName());
            TblDistrict district = districtRepository.findById(input.getDistrictId()).orElse(null);
            tblMerchantCorporate.setTblDistrict(district);
            tblMerchantCorporate.setAddressLine(input.getAddressLine());
            TblSettleBank settleBank = settleBankRepository.findById(input.getSettleBankId()).orElse(null);
            tblMerchantCorporate.setTblSettleBank(settleBank);
            tblMerchantCorporate.setPhoneNumber(input.getPhoneNumber());
            tblMerchantCorporate.setCreditorAccount(input.getCreditorAccount());
            tblMerchantCorporate.setModifiedByUser(getUserId());
            tblMerchantCorporate.setDateModified(new Date());
            tblMerchantCorporate.setStatus(MerchantStatus.APPROVED);
            TblMasterMerchant masterMerchant = masterMerchantRepository.findById(getTargetId()).orElse(null);
            tblMerchantCorporate.setTblMasterMerchant(masterMerchant);
            tblMerchantCorporate.setBranchAccountSettledType(EBranchAccountSettledType.CENTRALIZED);
            tblMerchantCorporate.setPaymentAcceptanceStatus(PaymentAcceptStatus.READY);

            TblMerchantCorporate savedData = merchantCorporateRepository.save(tblMerchantCorporate);
            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền tạo Merchant này"));

        }
    }
    public ResponseEntity<?> delete(Long id) {
        TblMerchantCorporate merchant = merchantCorporateRepository.findById(id).orElse(null);
        merchant.setStatus(MerchantStatus.DELETED);
        merchantCorporateRepository.save(merchant);
        return ResponseEntity.ok(new MessageResponse("Delete merchant"));
    }

    public ResponseEntity<?> merchantDetail(Long id) {

        TblMerchantCorporate merchant = merchantCorporateRepository.findById(id).orElse(null);

        if (merchant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Không tồn tại Merchant cá nhân này"));
        }
        return ResponseEntity.ok(fromEntity(merchant));
    }
}
