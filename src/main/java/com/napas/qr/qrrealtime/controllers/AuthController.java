package com.napas.qr.qrrealtime.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.entity.*;
import com.napas.qr.qrrealtime.payload.response.MessageResponse;
import com.napas.qr.qrrealtime.repository.UserRepository;
import com.napas.qr.qrrealtime.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.napas.qr.qrrealtime.payload.request.LoginRequest;
import com.napas.qr.qrrealtime.payload.response.JwtResponse;
import com.napas.qr.qrrealtime.security.jwt.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/mngweb/api/auth",produces = "application/json")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request,
                                              @Valid @RequestBody LoginRequest loginRequest) {


        TblOrgUser user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
        if (user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Thông tin tài khoản không hợp lệ"));
        }
        if (user.getStatus().equals(MerchantStatus.APPROVED)){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            TblMasterMerchant masterMerchant = userDetails.getMasterMerchant();
            TblMerchantCorporate merchantCorporate = userDetails.getMerchant();
            TblMerchantBranch merchantBranch = userDetails.getBranch();
            TblMerchantCashier cashier = userDetails.getCashier();
            TblMerchantPersonal personal = userDetails.getMerchantPersonal();

            String masterMerchantName = masterMerchant.getMmName();

            String branchName = merchantBranch != null ? merchantBranch.getBranchName() : "";
            String cashierName = cashier != null ? cashier.getCashierCode() : "";

            String merchantName = null;
            if (userDetails.getTargetType() != ETargetType.MASTER) {
                merchantName = merchantCorporate != null ? merchantCorporate.getName() : personal.getName();
            }

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getUsername(),
                    roles,
                    userDetails.getTargetType(),
                    userDetails.getTargetId(),
                    masterMerchantName,
                    merchantName,
                    branchName,
                    cashierName
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Bạn chưa có quyền login hệ thống này"));
    }
}
