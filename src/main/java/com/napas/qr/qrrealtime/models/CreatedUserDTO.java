package com.napas.qr.qrrealtime.models;

import com.napas.qr.qrrealtime.define.ERole;
import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import lombok.Data;

@Data
public class CreatedUserDTO {

    private String fullname;

    private String email;

    private MerchantStatus status;

    private String password;

    private ERole role;

    private ETargetType targetType;
}
