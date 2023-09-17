package com.napas.qr.qrrealtime.entity;

import com.napas.qr.qrrealtime.define.EBranchAccountSettledType;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "TBL_MERCHANT_CORPORATE")
@Data
@NamedQueries({
        @NamedQuery(name = "TblMerchantCorporate.findAll", query = "SELECT t FROM TblMerchantCorporate t")})
public class TblMerchantCorporate {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_MERCHANT_CORPORATE")
    @SequenceGenerator(sequenceName = "SEQ_TBL_MERCHANT_CORPORATE", allocationSize = 1, name = "SEQ_TBL_MERCHANT_CORPORATE")
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
    private Long createdByUser;

    @Column(name = "MODIFIED_BY_USER")
    private Long modifiedByUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "BRANCH_ACCOUNT_SETTLED_TYPE")
    private EBranchAccountSettledType branchAccountSettledType;

    @Column(name = "SETTLE_BANK_ID")
    private Long settleBankId;

    @Column(name = "CREDITOR_ACCOUNT")
    private String creditorAccount;

    @ManyToOne
    @JoinColumn(name = "tbl_master_merchant_id")
    private TblMasterMerchant tblMasterMerchant;

    public TblMasterMerchant getTblMasterMerchant() {
        return tblMasterMerchant;
    }

    public void setTblMasterMerchant(TblMasterMerchant tblMasterMerchant) {
        this.tblMasterMerchant = tblMasterMerchant;
    }
}
