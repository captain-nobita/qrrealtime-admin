package com.napas.qr.qrrealtime.entity;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "MERCHANT_CODE")
    private String merchantCode;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MM_ID")
    private Long mmId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private MerchantStatus status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NAME")
    private String name;
    @Size(max = 20)
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Size(max = 1000)
    @Column(name = "ADDRESS_LINE")
    private String addressLine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "DATE_MODIFIED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY_USER")
    private Long createdByUser;

    @Column(name = "MODIFIED_BY_USER")
    private Long modifiedByUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CREDITOR_ACCOUNT")
    private String creditorAccount;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_ACCEPTANCE_STATUS")
    private PaymentAcceptStatus paymentAcceptanceStatus;

    @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private District tblDistrict;

    @JoinColumn(name = "MM_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblMasterMerchant tblMasterMerchant;

    @JoinColumn(name = "SETTLE_BANK_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblSettleBank tblSettleBank;

    @Column(name = "OWNER_NAME")
    private String ownerName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

}
