package com.napas.qr.qrrealtime.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;

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

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "MM_CODE")
    private String mmCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "MM_NAME")
    private String mmName;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "VIEW_MERCHANT_PAYMENT")

    private String viewMerchantPayment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMasterMerchant")
    private Collection<TblMerchantCorporate> tblMerchantCorporateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMasterMerchant")
    private Collection<TblMerchantPersonal> tblMerchantPersonalCollection;

}
