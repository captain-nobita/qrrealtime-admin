/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.ERole;
import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import com.napas.qr.qrrealtime.entity.TblMerchantCashier;
import com.napas.qr.qrrealtime.entity.TblMerchantPersonal;
import com.napas.qr.qrrealtime.entity.TblOrgUser;
import com.napas.qr.qrrealtime.models.CreatedUserDTO;
import com.napas.qr.qrrealtime.models.OrgUserDTO;
import com.napas.qr.qrrealtime.models.TblMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.UserDetail;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.UserRepository;
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

        if (getUserDetails().getTargetType().equals(ETargetType.MASTER) && getERole().equals(ERole.ADMIN)) {

            Page<TblOrgUser> dbResult = userRepository.searchAdmin(paging, fullname, status, username);
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        } else {
            Page<TblOrgUser> dbResult = userRepository.search(paging, fullname, status, username, getUserDetails().getTargetType(), getTargetId());
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        }


    }

    public ResponseEntity<?> post(CreatedUserDTO input) {

        if (getUserDetails().getTargetType().equals(ETargetType.PERSONAL) && getERole().equals(ERole.ADMIN)) {
            TblOrgUser orgUser = new TblOrgUser();
            orgUser.setFullname(input.getFullname());
            orgUser.setCreatedByUser(getUserId());
            orgUser.setDateCreated(LocalDateTime.now());
            orgUser.setPassword(encoder.encode(input.getPassword()));
            orgUser.setUsername(input.getEmail());
            orgUser.setStatus(MerchantStatus.APPROVED);
            orgUser.setRole(input.getRole());
            orgUser.setTargetId(getTargetId());
            orgUser.setTargetType(ETargetType.PERSONAL);
            TblOrgUser saveData = userRepository.save(orgUser);

            return ResponseEntity.ok(fromEntity(saveData));
        } else if (getUserDetails().getTargetType().equals(ETargetType.MASTER) && getERole().equals(ERole.ADMIN)) {
            TblOrgUser orgUser = new TblOrgUser();
            orgUser.setFullname(input.getFullname());
            orgUser.setCreatedByUser(getUserId());
            orgUser.setDateCreated(LocalDateTime.now());
            orgUser.setPassword(encoder.encode(input.getPassword()));
            orgUser.setUsername(input.getEmail());
            orgUser.setStatus(MerchantStatus.APPROVED);
            orgUser.setRole(input.getRole());
            orgUser.setTargetId(getTargetId());
            orgUser.setTargetType(input.getTargetType());
            TblOrgUser saveData = userRepository.save(orgUser);
            return ResponseEntity.ok(fromEntity(saveData));
        }
        else if (getUserDetails().getTargetType().equals(ETargetType.MERCHANT) && getERole().equals(ERole.ADMIN)) {
            TblOrgUser orgUser = new TblOrgUser();
            orgUser.setFullname(input.getFullname());
            orgUser.setCreatedByUser(getUserId());
            orgUser.setDateCreated(LocalDateTime.now());
            orgUser.setPassword(encoder.encode(input.getPassword()));
            orgUser.setUsername(input.getEmail());
            orgUser.setStatus(MerchantStatus.APPROVED);
            orgUser.setRole(input.getRole());
            orgUser.setTargetId(getTargetId());
            orgUser.setTargetType(input.getTargetType());
            TblOrgUser saveData = userRepository.save(orgUser);
            return ResponseEntity.ok(fromEntity(saveData));
        } else if (getUserDetails().getTargetType().equals(ETargetType.BRANCH) && getERole().equals(ERole.ADMIN)) {
            TblOrgUser orgUser = new TblOrgUser();
            orgUser.setFullname(input.getFullname());
            orgUser.setCreatedByUser(getUserId());
            orgUser.setDateCreated(LocalDateTime.now());
            orgUser.setPassword(encoder.encode(input.getPassword()));
            orgUser.setUsername(input.getEmail());
            orgUser.setStatus(MerchantStatus.APPROVED);
            orgUser.setRole(input.getRole());
            orgUser.setTargetId(getTargetId());
            orgUser.setTargetType(input.getTargetType());
            TblOrgUser saveData = userRepository.save(orgUser);
            return ResponseEntity.ok(fromEntity(saveData));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Bạn chưa có quyền tạo hệ thống này"));

        }
    }

    public ResponseEntity<?> delete(Long id) {
        TblOrgUser tblOrgUser = userRepository.findById(id).orElse(null);

        if (tblOrgUser.getTargetType().equals(ETargetType.MERCHANT)) {
            if (getUserDetails().getTargetType() == ETargetType.MASTER && getERole().equals(ERole.ADMIN) || getUserDetails().getTargetType() == ETargetType.MERCHANT && getERole().equals(ERole.ADMIN)) {
                tblOrgUser.setStatus(MerchantStatus.DELETED);
                tblOrgUser.setModifiedByUser(getUserId());
                tblOrgUser.setDateModified(LocalDateTime.now());
                TblOrgUser saveData = userRepository.save(tblOrgUser);
                return ResponseEntity.ok(new MessageResponse("Delete Suscess"));

            }
        } else if (tblOrgUser.getTargetType().equals(ETargetType.BRANCH)) {
            if (getUserDetails().getTargetType() == ETargetType.MERCHANT && getERole().equals(ERole.ADMIN) || getUserDetails().getTargetType() == ETargetType.BRANCH && getERole().equals(ERole.ADMIN)) {
                tblOrgUser.setStatus(MerchantStatus.DELETED);
                tblOrgUser.setModifiedByUser(getUserId());
                tblOrgUser.setDateModified(LocalDateTime.now());
                userRepository.save(tblOrgUser);
                return ResponseEntity.ok(new MessageResponse("Delete Suscess"));


            }
        } else if (tblOrgUser.getTargetType().equals(ETargetType.CASHIER)) {
            if (getUserDetails().getTargetType() == ETargetType.BRANCH && getERole().equals(ERole.ADMIN)) {
                tblOrgUser.setStatus(MerchantStatus.DELETED);
                tblOrgUser.setModifiedByUser(getUserId());
                tblOrgUser.setDateModified(LocalDateTime.now());
                userRepository.save(tblOrgUser);
                return ResponseEntity.ok(new MessageResponse("Delete Suscess"));
            }
        } else if (tblOrgUser.getTargetType().equals(ETargetType.PERSONAL)) {
            if (getUserDetails().getTargetType() == ETargetType.PERSONAL && getERole().equals(ERole.ADMIN) || getUserDetails().getTargetType() == ETargetType.MASTER && getERole().equals(ERole.ADMIN)) {
                tblOrgUser.setStatus(MerchantStatus.DELETED);
                tblOrgUser.setModifiedByUser(getUserId());
                tblOrgUser.setDateModified(LocalDateTime.now());
                userRepository.save(tblOrgUser);
                return ResponseEntity.ok(new MessageResponse("Delete Suscess"));

            }
        }  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Bạn chưa có quyền xóa hệ thống này"));
    }
}
