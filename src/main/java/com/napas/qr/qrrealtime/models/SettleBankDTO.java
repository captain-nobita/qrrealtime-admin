package com.napas.qr.qrrealtime.models;

import lombok.Data;

import java.util.Date;

@Data
public class SettleBankDTO {
    private Long id;

    private String bankId;

    private String bankReveiveCode;

    private String status;

    private Date dateCreated;

    private Date dateModified;

    private Long createdByUser;

    private Long modifiedByUser;
}
