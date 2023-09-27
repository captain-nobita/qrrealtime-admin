/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.ERole;
import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.*;
import com.napas.qr.qrrealtime.models.CreatedUserDTO;
import com.napas.qr.qrrealtime.models.OrgUserDTO;
import com.napas.qr.qrrealtime.models.TblMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.UserDetail;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.*;
import com.napas.qr.qrrealtime.security.jwt.JwtUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author huynx
 */
@Service
public class UserService extends BaseService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private MasterMerchantRepository masterMerchantDAO;

    @Autowired
    private MerchantCorporateRepository merchantCorporateDAO;

    @Autowired
    private MerchantBranchRepository branchDAO;

    @Autowired
    private MerchantCashierRepository cashierDAO;

    @Autowired
    private ModelMapper modelMapper;

    private OrgUserDTO fromEntity(TblOrgUser entity) {
        OrgUserDTO dto = modelMapper.map(entity, OrgUserDTO.class);
        return dto;
    }

    public UserDetail get(Long id) {
        TblOrgUser user = userRepository.getOne(id);
        return new UserDetail(user.getId(), user.getUsername(),
                user.getDateCreated().toString(), user.getDateModified().toString(), user.getRole(), user.getFullname());
    }

    public UserDetail getByUsername(String userName) {
        TblOrgUser user = userRepository.findByUsername(userName).orElseThrow();
        return new UserDetail(user.getId(), user.getUsername(),
                user.getDateCreated().toString(), user.getDateModified().toString(), user.getRole(), user.getFullname());
    }

    public ResponseEntity<?> search(Pageable paging, String fullname, MerchantStatus status, String username) {
        TblOrgUser user = userRepository.findById(getUserId()).orElse(null);
        switch(user.getTargetType()) {
            case MASTER:
                TblMasterMerchant masterMerchant = masterMerchantDAO.findById(user.getTargetId())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "));
                TblMerchantCorporate merchantCorporate1 = merchantCorporateDAO.findFirstByTblMasterMerchant(masterMerchant);
                if (merchantCorporate1 == null){
                    Page<TblOrgUser> dbResult = userRepository.search(paging, fullname, status, username, masterMerchant.getId(), null);
                    return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
                }
                Page<TblOrgUser> dbResult = userRepository.search(paging, fullname, status, username, masterMerchant.getId(), merchantCorporate1.getId());
                return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
            case MERCHANT:
                TblMerchantCorporate merchantCorporate = merchantCorporateDAO.findById(user.getTargetId())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "));
                TblMerchantBranch merchantBranch1= branchDAO.findFirstByTblMerchantCorporate(merchantCorporate);
                if (merchantBranch1 == null){
                    Page<TblOrgUser> dbResult1 = userRepository.search(paging, fullname, status, username,merchantCorporate.getId(), null);
                    return ResponseEntity.ok(dbResult1.map(entity -> fromEntity(entity)));
                }
                Page<TblOrgUser> dbResult1 = userRepository.search(paging, fullname, status, username,merchantCorporate.getId(), merchantBranch1.getId());
                return ResponseEntity.ok(dbResult1.map(entity -> fromEntity(entity)));
            case BRANCH:
                TblMerchantBranch merchantBranch = branchDAO.findById(user.getTargetId())
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "));
                TblMerchantCashier cashier1 = cashierDAO.findFirstByTblMerchantBranch(merchantBranch);
                if (cashier1 == null){
                    Page<TblOrgUser> dbResult2 = userRepository.search(paging, fullname, status, username,merchantBranch.getId(),null);
                    return ResponseEntity.ok(dbResult2.map(entity -> fromEntity(entity)));
                }
                Page<TblOrgUser> dbResult2 = userRepository.search(paging, fullname, status, username,merchantBranch.getId(), cashier1.getId());
                return ResponseEntity.ok(dbResult2.map(entity -> fromEntity(entity)));
            case CASHIER:
                Page<TblOrgUser> dbResult3 = userRepository.searchUserCashierAndPersonal(paging, fullname, status, username,getTargetId());
                return ResponseEntity.ok(dbResult3.map(entity -> fromEntity(entity)));
            case PERSONAL:
                Page<TblOrgUser> dbResult4 = userRepository.searchUserCashierAndPersonal(paging, fullname, status, username,getTargetId());
                return ResponseEntity.ok(dbResult4.map(entity -> fromEntity(entity)));
        }
        return null;

    }

    public ResponseEntity<?> post(CreatedUserDTO input) {

            TblOrgUser orgUser = new TblOrgUser();
            if (input.getTargetType().equals(ETargetType.CASHIER)){
                if (input.getFullname() ==null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Tên đầy đủ bạn không được để trống"));
                }
                orgUser.setFullname(input.getFullname());
                orgUser.setCreatedByUser(getUserId());
                orgUser.setDateCreated(LocalDateTime.now());
                if (input.getPassword() == null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập password cho User này"));
                }
                orgUser.setPassword(encoder.encode(input.getPassword()));
                if (input.getEmail()== null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập Email cho User này"));
                }
                orgUser.setUsername(input.getEmail());
                orgUser.setStatus(MerchantStatus.APPROVED);

                orgUser.setRole(ERole.ADMIN);

                orgUser.setTargetId(input.getTargetId());
                if (input.getTargetType() == null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập TargetType cho User này"));
                }
                orgUser.setTargetType(input.getTargetType());
                TblOrgUser saveData = userRepository.save(orgUser);
                return ResponseEntity.ok(fromEntity(saveData));
            } else {
                if (input.getFullname() ==null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Tên đầy đủ bạn không được để trống"));
                }
                orgUser.setFullname(input.getFullname());
                orgUser.setCreatedByUser(getUserId());
                orgUser.setDateCreated(LocalDateTime.now());
                if (input.getPassword() == null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập password cho User này"));
                }
                orgUser.setPassword(encoder.encode(input.getPassword()));
                if (input.getEmail()== null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập Email cho User này"));
                }
                orgUser.setUsername(input.getEmail());
                orgUser.setStatus(MerchantStatus.APPROVED);
                if (input.getRole() == null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa gán Quyền cho user này"));
                }
                orgUser.setRole(input.getRole());

                orgUser.setTargetId(input.getTargetId());
                if (input.getTargetType() == null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa nhập TargetType cho User này"));
                }
                orgUser.setTargetType(input.getTargetType());
                TblOrgUser saveData = userRepository.save(orgUser);
                return ResponseEntity.ok(fromEntity(saveData));
            }
    }

    public ResponseEntity<?> delete(Long id) {
        TblOrgUser tblOrgUser = userRepository.findById(id).orElse(null);

        if (tblOrgUser.getTargetType().equals(ETargetType.MERCHANT)) {
            if (getUserDetails().getTargetType() == ETargetType.MASTER || getUserDetails().getTargetType() == ETargetType.MERCHANT && getERole().equals(ERole.ADMIN)) {
                tblOrgUser.setStatus(MerchantStatus.DELETED);
                tblOrgUser.setModifiedByUser(getUserId());
                tblOrgUser.setDateModified(LocalDateTime.now());
                TblOrgUser saveData = userRepository.save(tblOrgUser);
                return ResponseEntity.ok(new MessageResponse("Delete Suscess"));

            }
        } else if (tblOrgUser.getTargetType().equals(ETargetType.BRANCH)) {
            if (getUserDetails().getTargetType() == ETargetType.MERCHANT || getUserDetails().getTargetType() == ETargetType.BRANCH && getERole().equals(ERole.ADMIN)) {
                tblOrgUser.setStatus(MerchantStatus.DELETED);
                tblOrgUser.setModifiedByUser(getUserId());
                tblOrgUser.setDateModified(LocalDateTime.now());
                userRepository.save(tblOrgUser);
                return ResponseEntity.ok(new MessageResponse("Delete Suscess"));
            }
        } else if (tblOrgUser.getTargetType().equals(ETargetType.CASHIER)) {
            if (getUserDetails().getTargetType() == ETargetType.BRANCH) {
                tblOrgUser.setStatus(MerchantStatus.DELETED);
                tblOrgUser.setModifiedByUser(getUserId());
                tblOrgUser.setDateModified(LocalDateTime.now());
                userRepository.save(tblOrgUser);
                return ResponseEntity.ok(new MessageResponse("Delete Suscess"));
            }
        } else if (tblOrgUser.getTargetType().equals(ETargetType.PERSONAL)) {
            if (getUserDetails().getTargetType() == ETargetType.PERSONAL && getERole().equals(ERole.ADMIN) || getUserDetails().getTargetType() == ETargetType.MASTER) {
                tblOrgUser.setStatus(MerchantStatus.DELETED);
                tblOrgUser.setModifiedByUser(getUserId());
                tblOrgUser.setDateModified(LocalDateTime.now());
                userRepository.save(tblOrgUser);
                return ResponseEntity.ok(new MessageResponse("Delete Suscess"));

            }
        }  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Bạn chưa có quyền xóa hệ thống này"));
    }
}
