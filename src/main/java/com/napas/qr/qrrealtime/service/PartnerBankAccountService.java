package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.ECreateBankAccountRequestStatus;
import com.napas.qr.qrrealtime.entity.HisCreateBankAccountRequest;
import com.napas.qr.qrrealtime.models.HisCreateBankAccountRequestDTO;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.HisCreateBankAccountRequestDAO;
import java.util.Date;
import java.util.UUID;
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
        request.setSettleBankId(0);
        
        request = hisCreateBankAccountRequestDAO.save(request);
        
        return ResponseEntity.ok(modelMapper.map(request, HisCreateBankAccountRequestDTO.class));
    }
    
    public ResponseEntity<?> getRequestInfo(String id) {
        long userId = getUserDetails().getId();
        
        HisCreateBankAccountRequest request = hisCreateBankAccountRequestDAO.findByIdAndCreatedBy(id, userId).orElse(null);
        
        if(request == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        return ResponseEntity.ok(modelMapper.map(request, HisCreateBankAccountRequestDTO.class));
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
