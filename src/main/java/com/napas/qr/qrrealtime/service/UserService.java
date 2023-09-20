/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantBranch;
import com.napas.qr.qrrealtime.entity.TblMerchantCashier;
import com.napas.qr.qrrealtime.entity.TblOrgUser;
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

/**
 *
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

        if (getUserDetails().getTargetType().equals("MASTER") && getERole().equals("ADMIN")) {

            Page<TblOrgUser> dbResult = userRepository.searchAdmin(paging, fullname, status,username);
            return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));
        }
        Page<TblOrgUser> dbResult = userRepository.search(paging, fullname, status,username,getUserDetails().getTargetType(), getTargetId());
        return ResponseEntity.ok(dbResult.map(entity -> fromEntity(entity)));

    }
}
