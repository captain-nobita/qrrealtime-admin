package com.napas.qr.qrrealtime.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "TBL_PROVINCE")
public class Provice {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROVICE")
    @SequenceGenerator(sequenceName = "SEQ_PROVICE", allocationSize = 1, name = "SEQ_PROVICE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROV_NAME")
    private String provName;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "DATE_MODIFIED")
    private Date dateModified;
}
