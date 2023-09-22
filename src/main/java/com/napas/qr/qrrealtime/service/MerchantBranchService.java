package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.ERole;
import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import com.napas.qr.qrrealtime.entity.*;
import com.napas.qr.qrrealtime.models.CreatedMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.TblMerchantBranchDTO;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.MerchantBranchRepository;
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
public class MerchantBranchService extends BaseService {

    @Autowired
    private MerchantBranchRepository merchantBranchRepository;


    @Autowired
    private MerchantCorporateRepository merchantCorporateRepository;


    @Autowired
    private SettleBankRepository settleBankRepository;

    @Autowired
    private ModelMapper modelMapper;


    private TblMerchantBranchDTO fromEntity(TblMerchantBranch entity) {
        TblMerchantBranchDTO dto = modelMapper.map(entity, TblMerchantBranchDTO.class);
        dto.setSettleBankId(entity.getTblSettleBank().getId());
        return dto;
    }

    public ResponseEntity<?> search(Pageable paging, String branchName, MerchantStatus status, String branchCode) {
        TblMerchantCorporate merchantCorporate= getUserDetails().getMerchant();
        TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
        if (getUserDetails().getTargetType().equals(ETargetType.MERCHANT) ||getUserDetails().getTargetType().equals(ETargetType.BRANCH)&& getERole().equals(ERole.ADMIN)  ){
            Page<TblMerchantBranch> dbResult  = merchantBranchRepository.search(paging,branchName,status,branchCode, merchantCorporate.getId(), masterMerchant.getId() );
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền truy cập danh sách này"));
        }
    }

    public ResponseEntity<?> post(CreatedMerchantBranchDTO input) {

        TblMerchantBranch tblMerchantBranch = new TblMerchantBranch();

        if (getUserDetails().getTargetType() == ETargetType.MERCHANT && getERole().equals(ERole.ADMIN)) {

            TblMerchantCorporate merchant = getUserDetails().getMerchant();
            if (merchantBranchRepository.existsByBranchCode(input.getBranchCode())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("BranchCode đã tồn tại"));
            }
            tblMerchantBranch.setBranchCode(input.getBranchCode());
            TblMerchantCorporate merchantCorporate = merchantCorporateRepository.findById(merchant.getId()).orElse(null);

            tblMerchantBranch.setTblMerchantCorporate(merchantCorporate);
            tblMerchantBranch.setStatus(MerchantStatus.APPROVED);
            tblMerchantBranch.setDateCreated(new Date());
            tblMerchantBranch.setCreatedByUser(getUserId());
            if (input.getSettleBankId() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập ngân hàng thụ hưởng"));
            }
            TblSettleBank tblSettleBank = settleBankRepository.findById(input.getSettleBankId()).orElse(null);
            tblMerchantBranch.setTblSettleBank(tblSettleBank);
            tblMerchantBranch.setCreditorAccount(input.getCreditorAccount());
            tblMerchantBranch.setBranchName(input.getBranchName());
            tblMerchantBranch.setPaymentAcceptanceStatus(PaymentAcceptStatus.READY);

            TblMerchantBranch savedData = merchantBranchRepository.save(tblMerchantBranch);

            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền tạo Branch này"));

        }
    }

    public ResponseEntity<?> put(Long id,CreatedMerchantBranchDTO input) {

        TblMerchantBranch tblMerchantBranch = merchantBranchRepository.findById(id).orElse(null);

        if (getUserDetails().getTargetType() == ETargetType.MERCHANT && getERole().equals(ERole.ADMIN)) {


            tblMerchantBranch.setModifiedByUser(getUserId());
            tblMerchantBranch.setDateModified(new Date());
            if (input.getSettleBankId() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập ngân hàng thụ hưởng"));
            }
            TblSettleBank tblSettleBank = settleBankRepository.findById(input.getSettleBankId()).orElse(null);
            tblMerchantBranch.setTblSettleBank(tblSettleBank);
            tblMerchantBranch.setCreditorAccount(input.getCreditorAccount());
            tblMerchantBranch.setBranchName(input.getBranchName());

            TblMerchantBranch savedData = merchantBranchRepository.save(tblMerchantBranch);

            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền sửa Branch này"));

        }
    }
    public ResponseEntity<?> delete(Long id) {
        if (getUserDetails().getTargetType().equals(ETargetType.MERCHANT) || getUserDetails().getTargetType().equals(ETargetType.BRANCH) && getERole().equals(ERole.ADMIN) ){
            TblMerchantBranch merchant = merchantBranchRepository.findById(id).orElse(null);
            merchant.setStatus(MerchantStatus.DELETED);
            merchant.setDateModified(new Date());
            merchantBranchRepository.save(merchant);
            return ResponseEntity.ok(new MessageResponse("Delete merchant"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền xóa Branch này"));
        }
    }
}
