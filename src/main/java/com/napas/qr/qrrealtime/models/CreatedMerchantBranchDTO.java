package com.napas.qr.qrrealtime.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantCashier;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import com.napas.qr.qrrealtime.entity.TblSettleBank;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
public class CreatedMerchantBranchDTO {


    private String branchCode;


    private Long settleBankId;


    private String creditorAccount;

    private String branchName;


}
