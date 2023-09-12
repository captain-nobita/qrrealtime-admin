package com.napas.qr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "TBL_DISTRICT")
@Data
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DISTRICT")
    @SequenceGenerator(sequenceName = "SEQ_DISTRICT", allocationSize = 1, name = "SEQ_DISTRICT")
    private Long id;

    @Column(name = "PROV_ID")
    private Long provId;

    @Column(name = "DISTRICT_NAME")
    private String districtName;

    @Column(name = "DATE_CREATED")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date dateCreated;

    @Column(name = "DATE_MODIFIED")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date dateModified;
}
