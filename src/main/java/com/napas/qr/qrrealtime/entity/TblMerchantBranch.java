package com.napas.qr.qrrealtime.entity;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "TBL_MERCHANT_BRANCH")
public class TblMerchantBranch {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_MERCHANT_BRANCH")
    @SequenceGenerator(sequenceName = "SEQ_TBL_MERCHANT_BRANCH", allocationSize = 1, name = "SEQ_TBL_MERCHANT_BRANCH")
    @Column(name = "ID")
    private Long id;

    @Column(name = "BRANCH_CODE")
    private String branchCode;

    @Column(name = "MERCHANT_ID")
    private Long merchantId;


    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private MerchantStatus status;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "DATE_MODIFIED")
    private Date dateModified;

    @Column(name = "CREATED_BY_USER")
    private Long createdByUser;

    @Column(name = "MODIFIED_BY_USER")
    private Long modifiedByUser;

    @Column(name = "SETTLE_BANK_ID")
    private Long settleBankId;

    @Column(name = "CREDITOR_ACCOUNT")
    private String creditorAccount;

    @Column(name = "BRANCH_NAME")
    private String branchName;

    @Column(name = "PAYMENT_ACCEPTANCE_STATUS")
    private String paymentAcceptanceStatus;
}
