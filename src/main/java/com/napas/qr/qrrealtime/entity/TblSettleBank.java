package com.napas.qr.qrrealtime.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "TBL_SETTLE_BANK")
@Data
public class TblSettleBank {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_SETTLE_BANK")
    @SequenceGenerator(sequenceName = "SEQ_TBL_SETTLE_BANK", allocationSize = 1, name = "SEQ_TBL_SETTLE_BANK")
    @Column(name = "ID")
    private Long id;

    @Column(name = "BANK_ID")
    private String bankId;

    @Column(name = "BANK_RECEIVE_CODE")
    private String bankReveiveCode;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "DATE_MODIFIED")
    private Date dateModified;

    @Column(name = "CREATED_BY_USER")
    private Long createdByUser;

    @Column(name = "MODIFIED_BY_USER")
    private Long modifiedByUser;
}
