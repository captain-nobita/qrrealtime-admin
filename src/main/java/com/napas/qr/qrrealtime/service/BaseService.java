package com.napas.qr.qrrealtime.service;

import com.napas.qr.qrrealtime.entity.TblOrgUser;
import com.napas.qr.qrrealtime.repository.UserRepository;
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
}
