/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.napas.achoffline.reportoffline.service;

import com.napas.achoffline.reportoffline.entity.TblOrgUser;
import com.napas.achoffline.reportoffline.models.UserDetail;
import com.napas.achoffline.reportoffline.repository.UserRepository;
import com.napas.achoffline.reportoffline.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author huynx
 */
@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

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
}
