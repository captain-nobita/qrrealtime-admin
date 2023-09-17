package com.napas.qr.qrrealtime.models;

import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import lombok.Data;

import java.util.Date;

@Data
public class MerchantPersonalDetail {
    private String merchantCode;

    private String name;

    private String phoneNumber;

    private String addressLine;

    private Long districtId;

    private Date dateCreated;

    private Date dateModified;

    private Long createByUser;

    private Long modifiedByUser;

    private Long settleBankId;

    private String creditorAccount;

    private PaymentAcceptStatus paymentAcceptStatus;

    private String ownerName;

    private String emailAddress;
}
