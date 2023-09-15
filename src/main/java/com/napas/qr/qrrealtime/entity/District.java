package com.napas.qr.qrrealtime.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "TBL_DISTRICT")
@Data
public class District {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DISTRICT")
    @SequenceGenerator(sequenceName = "SEQ_DISTRICT", allocationSize = 1, name = "SEQ_DISTRICT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "PROV_ID")
    private Long provId;

    @Column(name = "DISTRICT_NAME")
    private String districtName;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "DATE_MODIFIED")
    private Date dateModified;
}
