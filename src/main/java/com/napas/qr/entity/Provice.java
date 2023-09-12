package com.napas.qr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name = "TBL_PROVINCE")
public class Provice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROVICE")
    @SequenceGenerator(sequenceName = "SEQ_PROVICE", allocationSize = 1, name = "SEQ_PROVICE")
    private Long id;

    @Column(name = "PROV_NAME")
    @Size(max = 5 )
    private String provName;

    @Column(name = "DATE_CREATED")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date dateCreated;

    @Column(name = "DATE_MODIFIED")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date dateModified;
}
