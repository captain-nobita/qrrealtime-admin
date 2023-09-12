package com.napas.qr.models;

import com.napas.qr.validation.PciCompliantPassword;
import lombok.Data;

@Data
public class PasswordDto {

    private  String token;

    @PciCompliantPassword
    private String password;
}
