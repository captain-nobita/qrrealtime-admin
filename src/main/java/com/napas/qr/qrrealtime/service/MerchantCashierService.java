package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import com.napas.qr.qrrealtime.entity.TblMerchantCashier;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import com.napas.qr.qrrealtime.models.CreatedMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.MerchantCashierDTO;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.MerchantBranchRepository;
import com.napas.qr.qrrealtime.repository.MerchantCashierRepository;
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
public class MerchantCashierService extends BaseService {

    @Autowired
    private MerchantCashierRepository merchantCashierRepository;


    @Autowired
    private ModelMapper modelMapper;

    private MerchantCashierDTO fromEntity(TblMerchantCashier entity) {
        MerchantCashierDTO dto = modelMapper.map(entity, MerchantCashierDTO.class);
        dto.setBranchName(entity.getTblMerchantBranch().getName());
        dto.setMerchantName(entity.getTblMerchantBranch().getTblMerchantCorporate().getName());
        return dto;
    }

    public ResponseEntity<?> search(Pageable paging, String cashierCode, MerchantStatus status) {

        TblMerchantBranch branch = getUserDetails().getBranch();
        if (getUserDetails().getTargetType().equals(ETargetType.BRANCH)) {
            Page<TblMerchantCashier> dbResult = merchantCashierRepository.search(paging, cashierCode, status, branch, null);
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        } else if (getUserDetails().getTargetType().equals(ETargetType.CASHIER)){
            Page<TblMerchantCashier> dbResult = merchantCashierRepository.search(paging, cashierCode, status, branch, getTargetId());
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền truy cập danh sách này"));
    }

    public ResponseEntity<?> post(MerchantCashierDTO input) {

        TblMerchantCashier tblMerchantCashier = new TblMerchantCashier();

        if (getUserDetails().getTargetType() == ETargetType.BRANCH && getUserDetails().getBranch().getStatus().equals(MerchantStatus.APPROVED)) {

            TblMerchantBranch branch = getUserDetails().getBranch();

            if (merchantCashierRepository.existsByCashierCodeAndTblMerchantBranch(input.getCashierCode(), branch)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Mã Code đã tồn tại"));
            }
            tblMerchantCashier.setCashierCode(input.getCashierCode().toUpperCase());
            tblMerchantCashier.setTblMerchantBranch(branch);
            tblMerchantCashier.setStatus(MerchantStatus.APPROVED);
            tblMerchantCashier.setDateCreated(new Date());
            tblMerchantCashier.setCreatedByUser(getUserId());

            TblMerchantCashier savedData = merchantCashierRepository.save(tblMerchantCashier);

            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền tạo Merchant này"));

        }
    }

    public ResponseEntity<?> put(Long id, MerchantCashierDTO input) {

        TblMerchantCashier tblMerchantCashier = merchantCashierRepository.findById(id).orElse(null);

        if (getUserDetails().getTargetType() == ETargetType.BRANCH && getUserDetails().getBranch().getStatus().equals(MerchantStatus.APPROVED)) {

            TblMerchantBranch branch = getUserDetails().getBranch();

            tblMerchantCashier.setTblMerchantBranch(branch);
            tblMerchantCashier.setStatus(MerchantStatus.APPROVED);
            tblMerchantCashier.setDateModified(new Date());
            tblMerchantCashier.setModifiedByUser(getUserId());

            TblMerchantCashier savedData = merchantCashierRepository.save(tblMerchantCashier);

            return ResponseEntity.ok(fromEntity(savedData));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền tạo Merchant này"));

        }
    }

    public ResponseEntity<?> delete(Long id) {
        if (getUserDetails().getTargetType() == ETargetType.BRANCH && getUserDetails().getBranch().getStatus().equals(MerchantStatus.APPROVED)) {
            TblMerchantCashier cashier = merchantCashierRepository.findById(id).orElse(null);
            cashier.setStatus(MerchantStatus.DELETED);
            cashier.setDateModified(new Date());
            merchantCashierRepository.save(cashier);
            return ResponseEntity.ok(new MessageResponse("Delete merchant"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn không có quyền tạo Merchant này"));
        }
    }
    public ResponseEntity<?> list(){
        TblMerchantBranch merchantBranch = getUserDetails().getBranch();
        if (merchantBranch != null){
            List<TblMerchantCashier> list = merchantCashierRepository.list(merchantBranch);
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Không tồn tại Master Merchant này"));
    }
    public ResponseEntity<?> checkCode(MerchantCashierDTO input){
        TblMerchantBranch branch = getUserDetails().getBranch();
        if (merchantCashierRepository.existsByCashierCodeAndTblMerchantBranch(input.getCashierCode(), branch)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Mã Code đã tồn tại"));
        }
        return ResponseEntity.ok("Thành Công");
    }
}
