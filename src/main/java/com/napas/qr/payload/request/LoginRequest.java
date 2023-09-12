package com.napas.qr.payload.request;

import com.napas.qr.validation.PciCompliantPassword;
import com.napas.qr.validation.ValidUsername;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @ValidUsername
    private String username;


    @PciCompliantPassword
    private String password;

    @NotBlank(message = "Captcha bị thiếu")
    private String captcha;
}
