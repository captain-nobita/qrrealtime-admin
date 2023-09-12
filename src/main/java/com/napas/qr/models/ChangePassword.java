package com.napas.qr.models;

import com.napas.qr.validation.PciCompliantPassword;
import lombok.Data;


@Data
public class ChangePassword {

    @PciCompliantPassword
    private String oldPassword;

    @PciCompliantPassword
    private String newPassword;

    @PciCompliantPassword
    private String confirmPassword;
}
