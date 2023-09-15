package com.napas.qr.qrrealtime.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "TBL_NAPAS_BANK")
public class TblNapasBank {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBL_NAPAS_BANK")
    @SequenceGenerator(sequenceName = "SEQ_TBL_NAPAS_BANK", allocationSize = 1, name = "SEQ_TBL_NAPAS_BANK")
    @Column(name = "ID")
    private Long id;

    @Column(name = "BIC")
    private String bic;

    @Column(name = "BANK_ID")
    private String bankId;

    @Column(name = "BEN_ID")
    private String benId;

    @Column(name = "BANK_SHORT_NAME")
    private String bankShortName;

    @Column(name = "BANK_FULL_NAME_VI")
    private String bankFullNameVi;

    @Column(name = "BANK_FULL_NAME_EN")
    private String bankFullNameEn;

}
