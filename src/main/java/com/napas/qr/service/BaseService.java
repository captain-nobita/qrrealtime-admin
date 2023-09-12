package com.napas.qr.service;

import com.napas.qr.entity.User;
import com.napas.qr.exception.ApiException;
import com.napas.qr.repository.UserRepository;
import com.napas.qr.security.jwt.MediaAuthencation;
import com.napas.qr.security.services.UserDetailsImpl;
import com.napas.qr.utils.ResponseCodeAndMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.print.Pageable;
import java.security.Principal;

@Slf4j
public class BaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;


    public MediaAuthencation getAuthentication() {
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication();
        User opt = userRepository.findByUsername(principal.getName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (MediaAuthencation) authentication;
    }

    public long getUserId() {

        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication();
        User opt = userRepository.findByUsername(principal.getName());

        return opt.getId();
    }

    public UserDetailsImpl getUserDetail() {

        MediaAuthencation authencation = getAuthentication();

        if (authencation == null)
            throw new ApiException(ResponseCodeAndMsg.BAD_REQUEST, "Không tồn tại thông tin người dùng");

        UserDetailsImpl userDetail = (UserDetailsImpl) authencation.getPrincipal();

        if (userDetail == null)
            throw new ApiException(ResponseCodeAndMsg.BAD_REQUEST, "Không tồn tại thông tin người dùng");

        return userDetail;
    }

    protected Pageable getPageable(int page, int pageSize, Sort sort) {
        return (Pageable) PageRequest.of(page - 1, pageSize, sort);
    }

    public SimpleMailMessage sendEmail(String To, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(To);
        helper.setSubject(subject);
        helper.setText(text);
        emailSender.send(message);
        return null;
    }

}
