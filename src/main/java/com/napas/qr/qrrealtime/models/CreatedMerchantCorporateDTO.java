package com.napas.qr.qrrealtime.models;

import lombok.Data;

@Data
public class CreatedMerchantCorporateDTO {

    private String merchantCode;

    private String name;

    private String phoneNumber;

    private String addressLine;

    private Long districtId;

    private Long settleBankId;

    private String creditorAccount;
}
