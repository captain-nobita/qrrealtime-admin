package com.napas.qr.qrrealtime.models;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.define.PaymentAcceptStatus;
import com.napas.qr.qrrealtime.entity.TblMerchantCashier;
import com.napas.qr.qrrealtime.entity.TblMerchantCorporate;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Data
public class TblMerchantBranchDTO {

    private Long id;

    private String branchCode;

    private MerchantStatus status;

    private Date dateCreated;

    private LocalDateTime dateModified;

    private long createdByUser;
    private Long modifiedByUser;
    private Integer settleBankId;
    private String creditorAccount;

    private String branchName;


    private PaymentAcceptStatus paymentAcceptanceStatus;

    private Collection<TblMerchantCashier> tblMerchantCashierCollection;

    private TblMerchantCorporate tblMerchantCorporate;
}
