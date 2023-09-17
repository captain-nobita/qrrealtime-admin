package com.napas.qr.qrrealtime.entity;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
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

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "BRANCH_CODE")
    private String branchCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CREATED")
    private LocalDateTime dateCreated;
    @Column(name = "DATE_MODIFIED")
    private LocalDateTime dateModified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_BY_USER")
    private long createdByUser;
    @Column(name = "MODIFIED_BY_USER")
    private Long modifiedByUser;
    @Column(name = "SETTLE_BANK_ID")
    private Integer settleBankId;
    @Size(max = 20)
    @Column(name = "CREDITOR_ACCOUNT")
    private String creditorAccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "BRANCH_NAME")
    private String branchName;
    @Size(max = 20)
    @Column(name = "PAYMENT_ACCEPTANCE_STATUS")
    private String paymentAcceptanceStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMerchantBranch")
    private Collection<TblMerchantCashier> tblMerchantCashierCollection;
    @JoinColumn(name = "MERCHANT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblMerchantCorporate tblMerchantCorporate;
}
