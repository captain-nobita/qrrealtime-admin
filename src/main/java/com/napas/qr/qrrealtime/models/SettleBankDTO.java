package com.napas.qr.qrrealtime.models;

import lombok.Data;


@Data
public class SettleBankDTO {

    private Long id;

    private String bankId;

    private String bankShortName;

    public SettleBankDTO(Long id,String bankId, String bankShortName) {
        this.id= id;
        this.bankId = bankId;
        this.bankShortName = bankShortName;
    }
}
