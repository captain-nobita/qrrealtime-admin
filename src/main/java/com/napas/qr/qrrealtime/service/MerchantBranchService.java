package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.ERole;
import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import com.napas.qr.qrrealtime.entity.*;
import com.napas.qr.qrrealtime.models.CreatedMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.TblMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.UpdateAccountBankDTO;
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
import java.util.List;


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
        if (entity.getTblSettleBank() != null){
            dto.setSettleBankId(entity.getTblSettleBank().getId());
            return dto;
        }
        return dto;
    }

    public ResponseEntity<?> search(Pageable paging, String branchName, MerchantStatus status, String branchCode) {
        TblMerchantCorporate merchantCorporate= getUserDetails().getMerchant();
        TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
        if (getUserDetails().getTargetType().equals(ETargetType.MERCHANT)){
            Page<TblMerchantBranch> dbResult  = merchantBranchRepository.search(paging,branchName,status,branchCode, merchantCorporate, null, masterMerchant);
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        } else if (getUserDetails().getTargetType().equals(ETargetType.BRANCH)){
            Page<TblMerchantBranch> dbResult  = merchantBranchRepository.search(paging,branchName,status,branchCode, merchantCorporate, getTargetId(), masterMerchant);
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền truy cập danh sách này"));
        }
    }

    public ResponseEntity<?> post(CreatedMerchantBranchDTO input) {

        TblMerchantBranch tblMerchantBranch = new TblMerchantBranch();

        if (getUserDetails().getTargetType() == ETargetType.MERCHANT) {

            TblMerchantCorporate merchant = getUserDetails().getMerchant();
            if (merchantBranchRepository.existsByBranchCodeAndTblMerchantCorporate(input.getBranchCode(), merchant)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Mã Code đã tồn tại"));
            }
            tblMerchantBranch.setBranchCode(input.getBranchCode().toUpperCase());
            TblMerchantCorporate merchantCorporate = merchantCorporateRepository.findById(merchant.getId()).orElse(null);

            tblMerchantBranch.setTblMerchantCorporate(merchantCorporate);
            tblMerchantBranch.setStatus(MerchantStatus.APPROVED);
            tblMerchantBranch.setDateCreated(new Date());
            tblMerchantBranch.setCreatedByUser(getUserId());
            tblMerchantBranch.setName(input.getName());
            tblMerchantBranch.setPaymentAcceptanceStatus(PaymentAcceptStatus.READY);

            TblMerchantBranch savedData = merchantBranchRepository.save(tblMerchantBranch);

            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền tạo Branch này"));

        }
    }

    public ResponseEntity<?> put(Long id,CreatedMerchantBranchDTO input) {

        TblMerchantBranch tblMerchantBranch = merchantBranchRepository.findById(id).orElse(null);

        if (getUserDetails().getTargetType() == ETargetType.MERCHANT) {


            tblMerchantBranch.setModifiedByUser(getUserId());
            tblMerchantBranch.setDateModified(new Date());
            tblMerchantBranch.setName(input.getName());

            TblMerchantBranch savedData = merchantBranchRepository.save(tblMerchantBranch);

            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền sửa Branch này"));
        }
    }
    public ResponseEntity<?>updateAccount(Long id, UpdateAccountBankDTO input){

        if (getUserDetails().getTargetType().equals(ETargetType.BRANCH) && getERole().equals(ERole.ADMIN)){

            TblMerchantBranch merchantBranch = merchantBranchRepository.findById(id).orElse(null);
            if (input.getSettleBankId() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa điền thông tin ngân hàng thụ hưởng"));
            }
            TblSettleBank settleBank = settleBankRepository.findById(input.getSettleBankId()).orElse(null);
            merchantBranch.setTblSettleBank(settleBank);
            if (input.getCreditorAccount().length()>20){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Số tài khoản không vượt quá 20 kí tự"));
            }
            if (input.getCreditorAccount().length()<8){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Số tài khoản không nhỏ hơn 8 kí tự"));
            }
            merchantBranch.setCreditorAccount(input.getCreditorAccount());

            merchantBranchRepository.save(merchantBranch);

            return ResponseEntity.ok(new MessageResponse("Suscess"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa có quyền Tạo STK cho branch này "));
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

    public ResponseEntity<?> list(){

        TblMerchantCorporate merchantCorporate = getUserDetails().getMerchant();
        if (getUserDetails().getTargetType().equals(ETargetType.MERCHANT)){
            List<TblMerchantBranch> list = merchantBranchRepository.get(merchantCorporate, null);
            return ResponseEntity.ok(list);
        } else if (getUserDetails().getTargetType().equals(ETargetType.BRANCH)){
            List<TblMerchantBranch> list = merchantBranchRepository.get(merchantCorporate,getTargetId());
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Không tồn tại Branch này"));
    }
    public ResponseEntity<?> checkCode(CreatedMerchantBranchDTO input){
        TblMerchantCorporate merchant = getUserDetails().getMerchant();
        if (merchantBranchRepository.existsByBranchCodeAndTblMerchantCorporate(input.getBranchCode(), merchant)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Mã Code đã tồn tại"));
        }
        return ResponseEntity.ok("Thành Công");
    }
}
