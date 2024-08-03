package com.napas.qr.qrrealtime.controllers;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/mngweb/api/portal/captcha",produces = "application/json")
@PreAuthorize("permitAll()")
public class CaptchaController {
    public static String SessionCaptchaAnswer = "captchaAnswer";

    @PostMapping
    public ResponseEntity<byte[]> generateCaptcha(HttpSession session) throws IOException {
        var captcha = new Captcha.Builder(240,70)
                .addText()
                .build();
        var byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(captcha.getImage(), "png",byteArrayOutputStream);
        session.setAttribute(SessionCaptchaAnswer, captcha.getAnswer());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(byteArrayOutputStream.toByteArray());
    }
}
