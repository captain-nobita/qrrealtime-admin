package com.napas.qr.controllers;

import com.napas.qr.define.UserStatusType;
import com.napas.qr.payload.request.LoginRequest;
import com.napas.qr.payload.response.JwtResponse;
import com.napas.qr.payload.response.MessageResponse;
import com.napas.qr.security.jwt.JwtUtils;
import com.napas.qr.security.services.UserDetailsImpl;
import com.napas.qr.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth",produces = "application/json")
@Slf4j
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/signin",consumes ={"application/json"})
    public ResponseEntity<?> authenticateUser(HttpServletRequest request,
                                              @Valid @RequestBody LoginRequest loginRequest) throws NamingException {

        try {
            var session = request.getSession();
            var captchAnswer = session.getAttribute(CaptchaController.SessionCaptchaAnswer);
            session.removeAttribute(CaptchaController.SessionCaptchaAnswer);
            if (captchAnswer == null || !captchAnswer.equals(loginRequest.getCaptcha())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Captcha Không khớp"));
            }
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(loginRequest.getUsername());
                    String jwt = jwtUtils.generateJwtToken(authentication);
                    List<String> roles = userDetails.getAuthorities().stream()
                            .map(item -> item.getAuthority())
                            .collect(Collectors.toList());
                    return ResponseEntity.ok(new JwtResponse(jwt,
                            userDetails.getId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(), roles));

        } catch (BadCredentialsException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Username hoặc password không hợp lệ"));
        }}
}
