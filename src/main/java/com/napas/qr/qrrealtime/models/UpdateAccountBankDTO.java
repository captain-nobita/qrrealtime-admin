package com.napas.qr.qrrealtime.models;

import lombok.Data;

@Data
public class UpdateAccountBankDTO {

    private Long settleBankId;

    private String creditorAccount;
}
