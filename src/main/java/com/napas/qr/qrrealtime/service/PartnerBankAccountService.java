package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.ECreateBankAccountRequestStatus;
import com.napas.qr.qrrealtime.entity.HisCreateBankAccountRequest;
import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import com.napas.qr.qrrealtime.entity.TblNapasBank;
import com.napas.qr.qrrealtime.entity.TblSettleBank;
import com.napas.qr.qrrealtime.models.HisCreateBankAccountRequestDTO;
import com.napas.qr.qrrealtime.models.TblMerchantBranchDTO;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.HisCreateBankAccountRequestDAO;
import java.util.Date;
import java.util.UUID;

import com.napas.qr.qrrealtime.repository.NapasBankRepository;
import com.napas.qr.qrrealtime.repository.SettleBankRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PartnerBankAccountService extends BaseService {
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private HisCreateBankAccountRequestDAO  hisCreateBankAccountRequestDAO;

    @Autowired
    private SettleBankRepository settleBankRepository;


    @Autowired
    private NapasBankRepository napasBankRepository;

    private HisCreateBankAccountRequestDTO fromEntity(HisCreateBankAccountRequest entity) {
        HisCreateBankAccountRequestDTO dto = modelMapper.map(entity, HisCreateBankAccountRequestDTO.class);
        if (entity.getSettleBankId()== null){
            return dto;
        }
        TblSettleBank tblSettleBank = settleBankRepository.findById(entity.getSettleBankId()).orElse(null);
        TblNapasBank napasBank = napasBankRepository.findByBankId(tblSettleBank.getBankId());
        dto.setBankName(napasBank.getBankShortName());
        return dto;

    }
    public ResponseEntity<?> createNewBankAccountRequest() {
        long userId = getUserDetails().getId();
        
        // Tạo một UUID ngẫu nhiên
        UUID randomUUID = UUID.randomUUID();

        String requestId = randomUUID.toString();
        
        HisCreateBankAccountRequest request = new HisCreateBankAccountRequest();
        request.setId(requestId);
        request.setCreatedBy(userId);
        request.setDateCreated(new Date());
        request.setStatus(ECreateBankAccountRequestStatus.WAIT);
        request.setSettleBankId(0L);
        
        request = hisCreateBankAccountRequestDAO.save(request);
        
        return ResponseEntity.ok(fromEntity(request));
    }
    
    public ResponseEntity<?> getRequestInfo(String id) {
        long userId = getUserDetails().getId();
        
        HisCreateBankAccountRequest request = hisCreateBankAccountRequestDAO.findByIdAndCreatedBy(id, userId).orElse(null);
        if(request == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        return ResponseEntity.ok(fromEntity(request));
    }

    public ResponseEntity<?> storeNewBankAccount(HisCreateBankAccountRequestDTO request){
        HisCreateBankAccountRequest his = hisCreateBankAccountRequestDAO.findById(request.getId()).orElse(null);
        
        if(his == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        his.setAccountNumber(request.getAccountNumber());
        his.setStatus(ECreateBankAccountRequestStatus.DONE);
        his.setDateModified(new Date());
        
        hisCreateBankAccountRequestDAO.save(his);
        
        
        return ResponseEntity.ok(new MessageResponse("Luu thong tin tai khoan thanh cong"));
    }
}
