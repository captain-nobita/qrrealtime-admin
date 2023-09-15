package com.napas.qr.qrrealtime.entity;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "TBL_MERCHANT_PERSONAL")
@Data
public class TblMerchantPersonal {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_MERCHANT_PERSONAL")
    @SequenceGenerator(sequenceName = "SEQ_TBL_MERCHANT_PERSONAL", allocationSize = 1, name = "SEQ_TBL_MERCHANT_PERSONAL")
    @Column(name = "ID")
    private Long id;

    @Column(name = "MERCHANT_CODE")
    private String merchantCode;

    @Column(name = "MM_ID")
    private Long mmId;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private MerchantStatus status;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS_LINE")
    private String addressLine;

    @Column(name = "DISTRICT_ID")
    private Long districtId;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "DATE_MODIFIED")
    private Date dateModified;

    @Column(name = "CREATED_BY_USER")
    private Long createByUser;

    @Column(name = "MODIFIED_BY_USER")
    private Long modifiedByUser;

    @Column(name = "SETTLE_BANK_ID")
    private Long settleBankId;

    @Column(name = "CREDITOR_ACCOUNT")
    private String creditorAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_ACCEPTANCE_STATUS")
    private PaymentAcceptStatus paymentAcceptStatus;

    @Column(name = "OWNER_NAME")
    private String ownerName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
}
