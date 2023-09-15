package com.napas.qr.qrrealtime.models;


import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TblMerchantPersonalDTO {

    private Long id;

    private String merchantCode;

    private Long mmId;

    private String name;

    private MerchantStatus status;

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
