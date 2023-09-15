package com.napas.qr.qrrealtime.entity;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "TBL_MASTER_MERCHANT")
public class TblMasterMerchant {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_MASTER_MERCHANT")
    @SequenceGenerator(sequenceName = "SEQ_TBL_MASTER_MERCHANT", allocationSize = 1, name = "SEQ_TBL_MASTER_MERCHANT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "MM_CODE")
    private String mmCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private MerchantStatus status;

    @Column(name = "MM_NAME")
    private String mmName;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "DATE_MODIFIED")
    private Date dateModified;

    @Column(name = "CREATED_BY_USER")
    private Long createdByUser;

    @Column(name = "MODIFIED_BY_USER")
    private Long modifiedByUser;

    @Column(name = "VIEW_MERCHANT_PAYMENT")
    private String viewMerchantPayment;
}
