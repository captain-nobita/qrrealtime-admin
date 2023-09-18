package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.define.ERole;
import com.napas.qr.qrrealtime.entity.TblOrgUser;
import com.napas.qr.qrrealtime.repository.UserRepository;
import com.napas.qr.qrrealtime.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Optional;

public class BaseService {

    @Autowired
    private UserRepository userRepository;

    public long getUserId() {

        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication();
        Optional<TblOrgUser> opt = userRepository.findByUsername(principal.getName());
        return opt.get().getId();
    }

    public long getTargetId(){
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication();
        Optional<TblOrgUser> opt = userRepository.findByUsername(principal.getName());
        return opt.get().getTargetId();
    }

    public ERole getERole(){
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication();
        Optional<TblOrgUser> opt = userRepository.findByUsername(principal.getName());
        return opt.get().getRole();
    }

    public UserDetailsImpl getUserDetails(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;
    }
}
