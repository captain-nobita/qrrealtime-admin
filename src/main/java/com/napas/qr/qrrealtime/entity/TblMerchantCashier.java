package com.napas.qr.qrrealtime.entity;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "TBL_MERCHANT_CASHIER")
@Data
public class TblMerchantCashier {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_MERCHANT_CASHIER")
    @SequenceGenerator(sequenceName = "SEQ_TBL_MERCHANT_CASHIER", allocationSize = 1, name = "SEQ_TBL_MERCHANT_CASHIER")
    @Column(name = "ID")
    private Long id;

    @Column(name = "BRANCH_ID")
    private Long branchId;

    @Column(name = "CASHIER_CODE")
    private String cashierCode;

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

    @ManyToOne
    @JoinColumn(name = "tbl_merchant_branch_id")
    private TblMerchantBranch tblMerchantBranch;

    public TblMerchantBranch getTblMerchantBranch() {
        return tblMerchantBranch;
    }

    public void setTblMerchantBranch(TblMerchantBranch tblMerchantBranch) {
        this.tblMerchantBranch = tblMerchantBranch;
    }
}
